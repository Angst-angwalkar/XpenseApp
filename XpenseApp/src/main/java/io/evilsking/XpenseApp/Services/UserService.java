package io.evilsking.XpenseApp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.evilsking.XpenseApp.Models.UserModel;
import io.evilsking.XpenseApp.Repositories.UserRepository;
//import io.evilsking.XpenseApp.RepositoryImpl.UserRepositoryImpl;


@Component
public interface UserService {
	
	UserModel saveUser(UserModel userModel);
	
	UserModel getUserById(Long userId);

	UserModel updateUserDetails(UserModel userModel, Long userId);
	
}
