package io.evilsking.ExpenseService.Services;

import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.Repositories.ExpenseRepository;
import io.evilsking.ExpenseService.dto.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ExpenseServices {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<ExpenseResponse> getAllUsersExpenses(Long userId){
        return expenseRepository.findByUserId(userId).stream()
                .map(expense ->
                    ExpenseResponse.builder()
                            .expenseId(expense.getExpenseId())
                            .category(expense.getCategory())
                            .userId(expense.getUserId())
                            .amount(expense.getAmount())
                            .priority(expense.getPriority())
                            .build()
                ).collect(Collectors.toList());
        }
    }
