package io.evilsking.ExpenseService.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExpenseExceptionController {



    @ExceptionHandler(value = ExpenseNotFoundException.class)
    protected ResponseEntity<Object> noSuchUserException(ExpenseNotFoundException expenseNotFoundException){
        CustomExpenseErrorMessage errorMsg = new CustomExpenseErrorMessage("NOT_FOUND_ERROR", expenseNotFoundException.getMessage());
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<Object> noSuchUserException(UserNotFoundException userNotFoundException){
        CustomExpenseErrorMessage errorMsg = new CustomExpenseErrorMessage("NOT_FOUND_ERROR", userNotFoundException.getMessage());
        errorMsg.setTimestamp(LocalDateTime.now());
        errorMsg.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
    }


}
