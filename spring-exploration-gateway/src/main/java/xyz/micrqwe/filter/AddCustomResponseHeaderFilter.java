package xyz.micrqwe.filter;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author <a href="mailto:wf2311@163.com">wf2311</a>
 * @since 2020/6/1 14:40.
 */
@Component
public class AddCustomResponseHeaderFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        TokenInfo tokenInfo = exchange.getAttribute(AuthorizeGatewayFilter.TOKEN_INFO_KEY);
//        if (tokenInfo != null) {
//            ServerHttpResponse response = exchange.getResponse();
//            if (tokenInfo.getAccessToken() != null) {
//                response.getHeaders().set("X-Access-Token", HttpUtils.encode(tokenInfo.getAccessToken()));
//            }
//            if (tokenInfo.getRefreshToken() != null) {
//                response.getHeaders().set("X-Refresh-Token", HttpUtils.encode(tokenInfo.getRefreshToken()));
//            }
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }
}
