package io.evilsking.XpenseApp.Exceptions.UserExceptions;


import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	
	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
}