package com.microservice.apigateway.apigatewayconfigs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator getRoute(RouteLocatorBuilder builder){

        return builder.routes()
                      .route(p -> p.path("/currency-conversion/**")
                                            .uri("lb://CURRENCY-CONVERSION-SERVICE"))
                      .route(p -> p.path("/currency-exchange/**")
                                   .uri("lb://CURRENCY-EXCHANGE-SERVICE"))
                      .build();
    }
}
