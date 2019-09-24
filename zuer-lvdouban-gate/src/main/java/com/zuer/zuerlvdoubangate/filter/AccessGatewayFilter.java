package com.zuer.zuerlvdoubangate.filter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

@Configuration
public class AccessGatewayFilter implements GlobalFilter {
    private static final String GATE_WAY_PREFIX = "/api";

    @Value("${gate.ignore.startWith}")
    private String startWith;
    @Value("${gate.tokenHeader}")
    private String tokenHeader;

    //@Autowired
    //private LoginController loginController;

    @Override
    public Mono filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        System.out.println("进入gateWay______________________");
        LinkedHashSet requiredAttribute = serverWebExchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = serverWebExchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();
        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()){
                URI next = iterator.next();
                if(next.getPath().startsWith(GATE_WAY_PREFIX)){
                    requestUri = next.getPath().substring(GATE_WAY_PREFIX.length());
                }
            }
        }
        final String method = request.getMethod().toString();
        //BaseContextHandler.setToken(null);
        ServerHttpRequest.Builder mutate = request.mutate();
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            ServerHttpRequest build = mutate.build();
            return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
        }
        return null;
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private void getUser(ServerHttpRequest request, ServerHttpRequest.Builder ctx) throws Exception {
        List<String> strings = request.getHeaders().get(tokenHeader);
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0);
        }

        return ;
    }
}
