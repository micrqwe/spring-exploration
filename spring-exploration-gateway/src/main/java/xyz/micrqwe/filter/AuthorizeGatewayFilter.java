//package xyz.micrqwe.filter;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpCookie;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.util.function.Consumer;
//
///**
// * @author <a href="mailto:wf2311@163.com">wf2311</a>
// * @since 2020/5/8 15:58.
// */
//public class AuthorizeGatewayFilter implements GlobalFilter, Ordered {
//
//    public static final int ORDER = 0;
//
//    private static final String AUTHORIZE_TOKEN = "token";
//    private static final String HEADER_TICKET_KEY = "X-Leke-Ticket";
//    private static final String TICKET = "ticket";
//    public static final String TOKEN_INFO_KEY = "tokenInfo";
//    public static final String DEVICE_TYPE_KEY = "X-Device-Type";
//
//    @Autowired
//    private JwtTokenService jwtTokenService;
//    @Autowired
//    private LekeAuthRemoteService lekeAuthRemoteService;
//
//    private static final String AUTH_ERROR_RESULT = HttpUtils.GSON.toJson(Result.error(CommonErrorCode.UNAUTHORIZED.getCode(), "身份验证无效，请登录"));
//
//    private boolean needAuth(ServerWebExchange exchange) {
//        return exchange.getRequest().getURI().getPath().contains("/auth/");
//    }
//
//    private Mono<Void> returnError(ServerHttpResponse response, int statusCode, String body) {
//        response.setRawStatusCode(statusCode);
//        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//        if (body != null) {
//            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = response.bufferFactory().wrap(bytes);
//            return response.writeWith(Flux.just(buffer));
//        }
//        return response.setComplete();
//
//    }
//
////    @Autowired
////    private JwtTokenManager jwtTokenManager;
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        if (!needAuth(exchange)) {
//            Mono<Void> vo = chain.filter(exchange);
//            return vo;
//        }
//        long l = System.currentTimeMillis();
//
//        ServerHttpResponse response = exchange.getResponse();
//        try {
//            String token = getLtToken(exchange);
//            String deviceType = exchange.getRequest().getHeaders().getFirst(DEVICE_TYPE_KEY);
//            BaseUserInfo userInfo = null;
//            if (token == null) {
//                String ticket = getLekeTicket(exchange);
//                if (ticket == null) {
//                    return returnError(response, HttpStatus.UNAUTHORIZED.value(), AUTH_ERROR_RESULT);
//                }
//                Result<Compound> result = lekeAuthRemoteService.checkTicket(ticket, deviceType);
//                if (!result.getSuccess()) {
//                    int statusCode = HttpStatus.UNAUTHORIZED.value();
//                    if (result.getCode() == CommonErrorCode.SERVER_DOWNGRADE_CODE.getCode()) {
//                        result.setCode(CommonErrorCode.SYSTEM_ERROR.getCode());
//                        result.setMessage("服务繁忙");
//                        statusCode = CommonErrorCode.SYSTEM_ERROR.getCode();
//                    }
//                    if (result.getMessage() == null) {
//                        result.setMessage(CommonErrorCode.SYSTEM_ERROR.getName());
//                    }
//                    return returnError(response, statusCode, HttpUtils.GSON.toJson(result));
//                }
//                Compound compound = result.getData();
//                userInfo = compound.getUser();
//                TokenInfo tokenInfo = compound.getToken();
//                String text = HttpUtils.GSON.toJson(userInfo);
//                if (tokenInfo != null) {
//                    exchange.getAttributes().put(TOKEN_INFO_KEY, tokenInfo);
//                }
//
//                Consumer<HttpHeaders> httpHeaders = httpHeader -> {
//                    httpHeader.set(HttpUtils.HEADER_USER_INFO_KEY, HttpUtils.encode(text));
//                };
//                ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
//                exchange.mutate().request(serverHttpRequest).build();
//            } else {
//                try {
//                    userInfo = jwtTokenService.parseUserInfo(token, deviceType);
////                    Jws<Claims> claims = jwtTokenManager.parseClaims(token);
////                   userInfo = JwtUtils.getUserInfo(claims);
//                } catch (UnauthorizedException e) {
//                    if(log.isDebugEnabled()){
//                        log.error("e",e);
//                    }
//                    Result error = Result.error(e.getError());
//                    return returnError(response, e.getStatus().value(), HttpUtils.GSON.toJson(error));
//                }
//                String text = HttpUtils.GSON.toJson(userInfo);
//
//                Consumer<HttpHeaders> httpHeaders = httpHeader -> httpHeader.set(HttpUtils.HEADER_USER_INFO_KEY, HttpUtils.encode(text));
//                ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
//                exchange.mutate().request(serverHttpRequest).build();
//            }
//
//        } catch (FeignKeepErrorException e) {
//            log.error(e.getError());
//            return returnError(response, e.getHttpCode(), e.getError());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return returnError(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
//        }
//        if (log.isDebugEnabled()) {
//            log.debug("url is check auth time:{}", System.currentTimeMillis() - l);
//        }
//
//        Mono<Void> vo = chain.filter(exchange);
//        return vo;
//    }
//
//    private String getLtToken(ServerWebExchange exchange) {
//        ServerHttpRequest request = exchange.getRequest();
//        HttpHeaders headers = request.getHeaders();
//        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
//        if (token == null) {
//            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
//            if (cookie != null) {
//                token = cookie.getValue();
//            }
//        }
//        if (token == null) {
//            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
//        }
//        return token;
//    }
//
//    private String getLekeTicket(ServerWebExchange exchange) {
//        ServerHttpRequest request = exchange.getRequest();
//        HttpHeaders headers = request.getHeaders();
//        String ticket = headers.getFirst(HEADER_TICKET_KEY);
//        if (ticket == null) {
//            HttpCookie cookie = request.getCookies().getFirst(TICKET);
//            if (cookie != null) {
//                ticket = cookie.getValue();
//            }
//        }
//        if (ticket == null) {
//            ticket = request.getQueryParams().getFirst(TICKET);
//        }
//        return ticket;
//    }
//
//    @Override
//    public int getOrder() {
//        return ORDER;
//    }
//}
