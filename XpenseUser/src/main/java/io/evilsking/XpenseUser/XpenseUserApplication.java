package io.evilsking.XpenseUser;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class XpenseUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(XpenseUserApplication.class, args);
	}
	
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = 
				new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		
		messageSource.setBasenames("messages");
		return messageSource;
	}

}
