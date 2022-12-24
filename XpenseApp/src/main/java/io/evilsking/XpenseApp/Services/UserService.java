package io.evilsking.XpenseApp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.evilsking.XpenseApp.Models.UserModel;
import io.evilsking.XpenseApp.Repositories.UserRepository;


@Component
public interface UserService {
	
	UserModel saveUser(UserModel userModel);
	
	UserModel getUserById(Long userId);

	UserModel updateUserDetails(UserModel userModel, Long userId);
	
	boolean deleteUser(Long userId);

	UserModel getUserByUserName(String userName);

	UserModel getUserByEmail(String email);
	
	UserModel getUserByMobileNo(String mobileNo);
	
	UserModel getUserByPanNo(String panNo);
	
	UserModel getUserByAadharNo(String aadharNo);
	
}
