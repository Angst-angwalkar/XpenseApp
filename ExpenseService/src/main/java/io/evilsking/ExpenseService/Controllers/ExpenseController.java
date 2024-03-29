package io.evilsking.ExpenseService.Controllers;


import io.evilsking.ExpenseService.AdvancedExpenseSearch.ExpenseSearchDto;
import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.Services.ExpenseServices;
import io.evilsking.ExpenseService.dto.ExpenseResponse;
import io.evilsking.ExpenseService.dto.MonthlyExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public ResponseEntity<String> addNewExpense(@RequestBody ExpenseModel expenseModel, @PathVariable Long userId){
        return new ResponseEntity<>(expenseServices.addNewUserExpense(expenseModel, userId), HttpStatus.OK);
    }


    @PutMapping("/{userId}/update/expense/{expenseId}")
    public ResponseEntity<ExpenseModel> updateExpense(@RequestBody ExpenseModel expenseModel, @PathVariable Long expenseId){
        return new ResponseEntity<>(expenseServices.updateExpense(expenseModel, expenseId), HttpStatus.OK);
    }


    @DeleteMapping("/{userId}/delete/expense/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId){
        return new ResponseEntity<>(expenseServices.deleteExpense(expenseId), HttpStatus.OK);
    }



    @GetMapping("/{userId}/get-expense/monthly/{currentDate}")
    public ResponseEntity<MonthlyExpenseResponse> getMonthlyExpense(@PathVariable Long userId, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date currentDate){
        return new ResponseEntity<MonthlyExpenseResponse>(expenseServices.getMonthlyExpense(userId, currentDate), HttpStatus.OK);
    }

    @PostMapping("{userId}/get-expense/search")
    public ResponseEntity<Page<ExpenseModel>> getFilteredExpenses(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestBody ExpenseSearchDto expenseSearchDto
            ){
        return new ResponseEntity<Page<ExpenseModel>>(expenseServices.getFilteredExpenses(userId, expenseSearchDto, pageNum, pageSize), HttpStatus.OK);
    }

}
