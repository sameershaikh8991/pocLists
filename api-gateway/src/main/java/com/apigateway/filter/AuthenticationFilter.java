package com.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouterValidator routeValidator;


    @Autowired
    private JwtUtils jwtUtils;


    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                boolean b = exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
                System.out.println("b: "+b);
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw  new RuntimeException("missing AUTHORIZATION Token");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);

                    try {
//                        restTemplate.getForObject("http://AUTH-SERVICE//auth/validate-token?token="+authHeader,String.class);
                        jwtUtils.validateToken(authHeader);
                    }
                    catch (Exception ex){
                        System.out.println("exception:"+ex.getMessage());
                        throw  new RuntimeException(ex.getMessage());
                    }

                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }
}
