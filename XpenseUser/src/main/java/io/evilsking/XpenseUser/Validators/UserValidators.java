package io.evilsking.XpenseUser.Validators;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import io.evilsking.XpenseUser.Models.UserModel;



public class UserValidators{
	
	
	public String userNameValidator(UserModel userModel) {
		
		
		String userName = userModel.getUserName();
		System.out.println(userName);
		
		if (userName.length() == 0){
			return "Username cannot be empty";
		}
		
		else if (userName.length() > 15 || userName.length() < 8) {
			return "Username must be between 8-15 characters.";
		}
		
		else {
			return "OK";
		}
	};
	
	public String emailValidator(UserModel userModel) {
		String email = userModel.getEmail();
		
		
		if(email.length() == 0 || email == "") {
			return "Email Address is a mandatory field!";
		}
		
		else if (email.contains("@") == false) {
			return "Please enter a valid email address!";
		}
		
		else if (email.contains("test") == true) {
			return "Test emails are not allowed!";
		}

		else {
			return "OK";
		}
	};
	
	public String panValidator(UserModel userModel) {
		String panNo = userModel.getPanNo();
		
		
		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		Matcher matcher = pattern.matcher(panNo);
		
		if (panNo.length() == 0 || panNo.equals("") == true) {
			return "PAN is a mandatory field!";
		}
		else if (matcher.matches() == false) {
			return "Invalid PAN No.!";
		}
		else {
			return "OK";
		}
	};
	
	public String aadharValidator(UserModel userModel) {
		String aadhar = userModel.getAadharNo();
		
		Pattern pattern = Pattern.compile("[0-9]{12}");
		Matcher matcher = pattern.matcher(aadhar);
		
		if (aadhar.length() == 0 || aadhar.equals("") == true) {
			return "Aadhar is a mandatory field!";
		}
		else if (aadhar.length() > 12 || matcher.matches() == false) {
			return "Please enter a valid Aadhar No.!";
		}
		else {
			return "OK";
		}
	};
	
	public String mobileValidator(UserModel userModel) {
		String mobile = userModel.getMobileNo();
		
//		Pattern pattern = Pattern.compile("/^([+]\\d{2})?\\d{10}$/");
//		Matcher matcher = pattern.matcher(mobile);
		
		if (mobile.length() == 0 || mobile.equals("") == true) {
			return "Mobile Number is a mandatory field";
		}
		else if (mobile.length() >= 15) {
			return "Mobile Number cannot be more than 10 Digit Indian number";
		}
//		else if (matcher.matches() == false) {
//			return "Please enter a valid mobile number!";
//		}
		else {
			return "OK";
		}
		
		
	};
	
	public boolean userValidate(UserModel userModel) {
		return true;
	}
	
}
