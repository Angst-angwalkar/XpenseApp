package io.evilsking.ExpenseService.Validators;

import io.evilsking.ExpenseService.Models.ExpenseModel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ExpenseValidator {


    public boolean validateUser(ExpenseModel expenseModel){
        return true;
    }

    public boolean validateCategory(ExpenseModel expenseModel){
        return true;
    }

    public boolean dateFormatter(ExpenseModel expenseModel){
        return true;
    }



}
