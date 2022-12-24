package io.evilsking.XpenseApp.Exceptions.UserExceptions;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

}