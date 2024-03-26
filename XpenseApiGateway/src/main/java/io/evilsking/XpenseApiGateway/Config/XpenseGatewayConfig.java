package io.evilsking.XpenseApiGateway.Config;


import io.github.resilience4j.ratelimiter.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class XpenseGatewayConfig {



    @Autowired
    private ReactiveResilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory;

    @Autowired
    private RateLimiter rateLimiter;


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("user-service", r -> r.path("/api/user/**")
                        .filters(f -> f.circuitBreaker(
                                c -> c.setName("user-fallback").setFallbackUri("/forward:/fallback"))
//                                .requestRateLimiter(limiter -> limiter.setRateLimiter(rateLimiter))
                        )
                        .uri("lb://user-service"))
                .route("expense-service", r -> r.path("/api/expense/**")
                        .filters(f -> f.circuitBreaker(
                                        c -> c.setName("expense-fallback").setFallbackUri("/forward:/fallback"))
//                                .requestRateLimiter(limiter -> limiter.setRateLimiter(rateLimiter))
                        )
                        .uri("lb://expense-service"))
                .build();

    }


    @Bean
    public HandlerFunction<ServerResponse> fallback(){
        return request -> ServerResponse.ok().bodyValue("Failed to execute this request.");
    }



}
