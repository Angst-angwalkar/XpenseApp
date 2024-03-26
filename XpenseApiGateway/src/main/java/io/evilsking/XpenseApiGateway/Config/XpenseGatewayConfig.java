package io.evilsking.XpenseApiGateway.Config;


import io.github.resilience4j.ratelimiter.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class XpenseGatewayConfig {

    @Autowired
    private ReactiveResilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory;

//    @Autowired
//    private RateLimiter rateLimiter;


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("xpense-discovery-service", spec -> spec.path("/eureka/web")
                        .filters(f -> f.circuitBreaker(
                                        c -> c.setName("xpense-discovery-cb").setFallbackUri("/fallback")
                                )
                        )
                        .uri("http://localhost:8761"))
                .route("user-service", spec -> spec.path("/api/user/**")
                        .filters(f -> f.circuitBreaker(
                                c -> c.setName("user-cb").setFallbackUri("/fallback")
                                )
                        )
                        .uri("lb://user-service"))
                .route("expense-service", spec -> spec.path("/api/expense/**")
                        .filters(f -> f.circuitBreaker(
                                c -> c.setName("expense-service").setFallbackUri("/fallback")
                                )
                        )
                        .uri("lb://expense-service"))
                .route("xpense-discovery-service-static", spec -> spec.path("/eureka/**")
                        .filters(f -> f.circuitBreaker(
                                        c -> c.setName("xpense-discovery-cb").setFallbackUri("/fallback")
                                )
                        )
                        .uri("http://localhost:8761"))
                .build();
    }


    @Bean
    public HandlerFunction<ServerResponse> fallback(){
        return request -> ServerResponse.ok().bodyValue("Failed to execute this request.");
    }



}
