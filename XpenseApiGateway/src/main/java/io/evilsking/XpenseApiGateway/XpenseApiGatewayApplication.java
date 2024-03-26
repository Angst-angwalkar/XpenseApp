package io.evilsking.XpenseApiGateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
public class XpenseApiGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(XpenseApiGatewayApplication.class, args);

    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }


}
