package cn.strong.lt.gateway.filter;

import cn.strong.common.utils.HttpUtils;
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
@Slf4j
@Component
public class TraceGatewayFilter implements GlobalFilter, Ordered {
    private static final int ORDER = AuthorizeGatewayFilter.ORDER -1;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String traceId = request.getHeaders().getFirst(HttpUtils.TRACE_ID_KEY);
        if(log.isDebugEnabled()){
            log.debug("trace gateway running:{},url:{}",traceId,request.getURI().getPath());
        }
        if (traceId == null) {
            Consumer<HttpHeaders> httpHeaders = httpHeader -> httpHeader.set(HttpUtils.TRACE_ID_KEY, UUID.randomUUID().toString());
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
            exchange.mutate().request(serverHttpRequest).build();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
