package io.evilsking.ExpenseService;

import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.Repositories.ExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ExpenseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseServiceApplication.class, args);
	}

}
