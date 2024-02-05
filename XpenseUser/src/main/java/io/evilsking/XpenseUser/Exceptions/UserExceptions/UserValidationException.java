package io.evilsking.XpenseUser.Exceptions.UserExceptions;


public class UserValidationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public UserValidationException(String msg) {
		super(msg);
	}
	
}
