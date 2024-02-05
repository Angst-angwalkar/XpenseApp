package io.evilsking.XpenseUser;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
