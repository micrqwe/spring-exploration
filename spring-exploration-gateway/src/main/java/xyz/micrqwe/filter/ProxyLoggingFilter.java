package xyz.micrqwe.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @since 2020/5/16 10:33.
 */
// */
@Component
@Slf4j(topic = "proxyFile")
public class ProxyLoggingFilter implements GlobalFilter, Ordered {
    private static final String START_TIME = "startTime";
    private static final String SERVICE_ID = "serviceId";

    private String getServiceId(ServerWebExchange exchange) {
        Route route = (Route) exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        if (route == null) {
            return "unknown";
        }
        String id = route.getId();
        String[] split = id.split("_", 2);
        if (split.length > 1) {
            return split[1];
        }
        return id;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        exchange.getAttributes().put(SERVICE_ID, getServiceId(exchange));
        if (log.isDebugEnabled()) {
            log.debug("proxy is run url:{},serviceId:{}", exchange.getRequest().getURI().getPath(), exchange.getAttribute(SERVICE_ID));
        }
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                String serviceId =  exchange.getAttribute(SERVICE_ID);
                Long executeTime = (System.currentTimeMillis() - startTime);
                ServerHttpRequest request = exchange.getRequest();
                ServerHttpResponse response = exchange.getResponse();
                Integer statusCode = response.getRawStatusCode();
                String traceId = request.getHeaders().getFirst("HttpUtils.TRACE_ID_KEY");
                String info = String.format("Route:{%s} Method:{%s} Host:{%s} Path:{%s} Query:{%s} Spent:{%s} Status:{%s} Trace:{%s}",
                        serviceId,
                        request.getMethod().name(),
                        request.getURI().getHost(),
                        request.getURI().getPath(),
                        request.getQueryParams(), executeTime, statusCode, traceId);
                log.info(info);
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
