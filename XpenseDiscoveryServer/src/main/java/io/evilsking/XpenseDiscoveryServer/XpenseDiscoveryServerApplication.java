package io.evilsking.XpenseDiscoveryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class XpenseDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(XpenseDiscoveryServerApplication.class, args);
	}

}
