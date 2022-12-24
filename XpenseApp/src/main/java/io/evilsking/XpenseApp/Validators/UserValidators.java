package io.evilsking.XpenseApp.Validators;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import io.evilsking.XpenseApp.Repositories.UserRepository;
import io.evilsking.XpenseApp.Exceptions.UserExceptions.UserAlreadyExistsException;
import io.evilsking.XpenseApp.Models.UserModel;



public class UserValidators{
	
	
	public String userNameValidator(UserModel userModel) {
		
		String userName = userModel.getUserName();
		
		if (userName.length() == 0){
			return "Username cannot be empty";
		}
		
		else if (userName.length() > 15) {
			return "Username must not be more than 15 characters.";
		}
		
		else if (Pattern.matches("[a-zA-Z0-9]{10}", userName) == false){
			return "Username must contain only numbers and alphabets!";
		}
		
		else {
			return "OK";
		}
	};
	
	public void emailValidator(UserModel userModel) {};
	
	public void panValidator(UserModel userModel) {};
	
	public void aadharValidator(UserModel userModel) {};
	
	public void mobileValidator(UserModel userModel) {};
	
	public boolean userValidate(UserModel userModel) {
		return true;
	}
	
}
