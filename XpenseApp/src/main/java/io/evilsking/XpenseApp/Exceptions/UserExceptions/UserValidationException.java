package io.evilsking.XpenseApp.Exceptions.UserExceptions;


import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserValidationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UserValidationException(String msg) {
		super(msg);
	}
	
}
