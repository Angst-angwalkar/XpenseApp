package io.evilsking.ExpenseService.Exceptions;

public class ExpenseNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExpenseNotFoundException(String msg) {
        super(msg);
    }

}
