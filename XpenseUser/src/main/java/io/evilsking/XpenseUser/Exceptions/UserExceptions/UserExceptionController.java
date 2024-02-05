package io.evilsking.XpenseUser.Exceptions.UserExceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.evilsking.XpenseUser.Exceptions.CustomErrorMessage;

@RestControllerAdvice
public class UserExceptionController {

	@ExceptionHandler(value = { UserNotFoundException.class })
	protected ResponseEntity<Object> noSuchUserException(UserNotFoundException userNotFoundException){
		CustomErrorMessage errorMsg = new CustomErrorMessage("NOT_FOUND_ERROR", userNotFoundException.getMessage());
		errorMsg.setTimestamp(LocalDateTime.now());
		errorMsg.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { UserAlreadyExistsException.class })
	protected ResponseEntity<Object> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
		CustomErrorMessage errorMsg = new CustomErrorMessage("USER_ALREADY_EXISTS_ERROR", userAlreadyExistsException.getMessage());
		errorMsg.setTimestamp(LocalDateTime.now());
		errorMsg.setStatus(HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = {UserValidationException.class})
	protected ResponseEntity<Object> userValidationException(UserValidationException userValidationException){
		CustomErrorMessage errorMsg = new CustomErrorMessage("VALIDATION_FAILED_ERROR", userValidationException.getMessage());
		errorMsg.setTimestamp(LocalDateTime.now());
		errorMsg.setStatus(HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(errorMsg, HttpStatus.CONFLICT);
	}
	
	
	
}
