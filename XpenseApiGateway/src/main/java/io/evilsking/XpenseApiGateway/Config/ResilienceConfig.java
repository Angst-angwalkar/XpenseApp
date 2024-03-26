package io.evilsking.XpenseApiGateway.Config;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public ReactiveResilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory(){
        ReactiveResilience4JCircuitBreakerFactory factory = new ReactiveResilience4JCircuitBreakerFactory();
            factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(TimeLimiterConfig.custom()
                            .timeoutDuration(Duration.ofSeconds(10))
                            .build())
                    .circuitBreakerConfig(CircuitBreakerConfig.custom()
                            .failureRateThreshold(50)
                            .waitDurationInOpenState(Duration.ofSeconds(5))
                            .permittedNumberOfCallsInHalfOpenState(5)
                            .automaticTransitionFromOpenToHalfOpenEnabled(true)
                            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                            .slidingWindowSize(5)
                            .build())
                    .build());
            return factory;
    }


//    @Bean
//    public RateLimiterRegistry rateLimiterRegistry(){
//        return RateLimiterRegistry.ofDefaults();
//    }
//
//    @Bean
//    public RateLimiter rateLimiter(RateLimiterRegistry rateLimiterRegistry){
//        return rateLimiterRegistry.rateLimiter("gateway-limiter", RateLimiterConfig.custom().
//                limitForPeriod(10)
//                .limitRefreshPeriod(Duration.ofSeconds(1))
//                .build());
//    }
}
