package io.evilsking.XpenseUser.Services;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import io.evilsking.XpenseUser.Models.UserModel;


@Component
public interface UserService {
	
	String saveUser(UserModel userModel, Locale locale);
	
	UserModel getUserById(Long userId);

	UserModel updateUserDetails(UserModel userModel, Long userId);
	
	boolean deleteUser(Long userId);

	UserModel getUserByUserName(String userName);

	UserModel getUserByEmail(String email);
	
	UserModel getUserByMobileNo(String mobileNo);
	
	UserModel getUserByPanNo(String panNo);
	
	UserModel getUserByAadharNo(String aadharNo);

	List<UserModel> getAllUsers();
	
}
