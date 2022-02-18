package org.brutforcer.service.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfig {

    @Bean
    public RouteLocator router(RouteLocatorBuilder routeBuilder) {
        return routeBuilder.routes()
                .route("user_service",
                        route -> route.path("/user-service/**")
                                .uri("lb://user-service"))
                .build();
    }
}
