package io.evilsking.XpenseApp.Exceptions;

import io.evilsking.XpenseApp.Exceptions.UserNotFoundException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionController {

	@ExceptionHandler(value = { UserNotFoundException.class })
	protected ResponseEntity<Object> noSuchUserException(UserNotFoundException userNotFoundException){
		CustomErrorMessage errorMsg = new CustomErrorMessage("NOT_FOUND_ERROR", userNotFoundException.getMessage());
		errorMsg.setTimestamp(LocalDateTime.now());
		errorMsg.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorMsg, HttpStatus.NOT_FOUND);
	}
	
}
