package xyz.micrqwe.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:wf2311@163.com">wf2311</a>
 * @since 2020/5/11 15:15.
 */
@Component
@Slf4j
public class TraceGatewayFilter implements GlobalFilter, Ordered {
    private static final int ORDER = 100 - 1;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String traceId = request.getHeaders().getFirst("HttpUtils.TRACE_ID_KEY");
//        exchange.getRequest().getRemoteAddress().getHostName()
        // 添加客户端IP
//        if(log.isDebugEnabled()){
//            log.debug("trace gateway running:{},url:{}",traceId,request.getURI().getPath());
//        }
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        if (traceId == null) {
            Consumer<HttpHeaders> httpHeaders = httpHeader -> httpHeader.set("HttpUtils.TRACE_ID_KEY", UUID.randomUUID().toString());
            builder.headers(httpHeaders);
        }
        ServerHttpRequest ipRequest = builder
                .header("X-Real-IP", exchange.getRequest().getRemoteAddress().getAddress().getHostAddress())
                .header("X-Real-Name", exchange.getRequest().getRemoteAddress().getHostName())
                .build();

        exchange.mutate().request(ipRequest).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
