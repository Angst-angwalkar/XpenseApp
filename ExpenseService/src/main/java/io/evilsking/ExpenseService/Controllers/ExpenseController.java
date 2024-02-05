package io.evilsking.ExpenseService.Controllers;


import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.Services.ExpenseServices;
import io.evilsking.ExpenseService.dto.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ExpenseController {

    @Autowired
    private ExpenseServices expenseServices;

    @GetMapping("/{userId}/expense")
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses(@PathVariable Long userId){
        return new ResponseEntity<List<ExpenseResponse>>(expenseServices.getAllUsersExpenses(userId), HttpStatus.OK);
    }




}
