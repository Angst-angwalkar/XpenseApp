package io.evilsking.XpenseApiGateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class XpenseApiGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(XpenseApiGatewayApplication.class, args);

    }


}
