package io.evilsking.ExpenseService.Controllers;


import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.Services.ExpenseServices;
import io.evilsking.ExpenseService.dto.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseServices expenseServices;

    @GetMapping("/{userId}/details")
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses(@PathVariable Long userId){
        return new ResponseEntity<>(expenseServices.getAllUsersExpenses(userId), HttpStatus.OK);
    }


    @PostMapping("/{userId}/new/expense")
    public ResponseEntity<String> addNewExpense(@RequestBody ExpenseModel expenseModel){
        return new ResponseEntity<>(expenseServices.addNewUserExpense(expenseModel), HttpStatus.OK);
    }


    @PutMapping("/{userId}/update/expense/{expenseId}")
    public ResponseEntity<ExpenseModel> updateExpense(@RequestBody ExpenseModel expenseModel, @PathVariable Long expenseId){
        return new ResponseEntity<>(expenseServices.updateExpense(expenseModel, expenseId), HttpStatus.OK);
    }


    @DeleteMapping("/{userId}/delete/expense/{expenseId}")
    public ResponseEntity<String> updateExpense(@PathVariable Long expenseId){
        return new ResponseEntity<>(expenseServices.deleteExpense(expenseId), HttpStatus.OK);
    }




}
