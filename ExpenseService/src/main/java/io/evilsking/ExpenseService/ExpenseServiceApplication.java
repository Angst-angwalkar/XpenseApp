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

	@Bean
	public CommandLineRunner loadData(ExpenseRepository expenseRepository){
		return args -> {
			ExpenseModel expenseModel  = new ExpenseModel();
			expenseModel.setExpenseId(86811312L);
			expenseModel.setAmount(1000L);
			expenseModel.setUserId(1L);
			expenseModel.setCategory("Medical");
			expenseModel.setPriority(10L);

			ExpenseModel expenseModel2  = new ExpenseModel();
			expenseModel2.setExpenseId(868112L);
			expenseModel2.setAmount(11110L);
			expenseModel2.setCategory("Travel");
			expenseModel2.setPriority(7L);
			expenseModel2.setUserId(1L);

			expenseRepository.save(expenseModel);
			expenseRepository.save(expenseModel2);
		};
	}

}
