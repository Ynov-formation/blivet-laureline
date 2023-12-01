package com.example.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("account-service", r -> r.path("/account/**")
                        .uri("lb://ms-account"))
                .route("security-service", r -> r.path("/auth/**")
                        .uri("http://localhost:8089"))
                .route("customer-service", r -> r.path("/customer/**")
                        .uri("lb://ms-customer"))
                .route("operation-service", r -> r.path("/operation/**")
                        .uri("lb://MS-ORDER"))
                .build();
    }
}
