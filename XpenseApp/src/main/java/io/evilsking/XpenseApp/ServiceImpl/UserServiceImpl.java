package io.evilsking.XpenseApp.ServiceImpl;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import io.evilsking.XpenseApp.Exceptions.UserNotFoundException;
import io.evilsking.XpenseApp.Models.UserModel;
import io.evilsking.XpenseApp.Repositories.UserRepository;
import io.evilsking.XpenseApp.Services.UserService;

@Repository
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	public UserModel saveUser(UserModel userModel) {
		userRepository.save(userModel);
		return userModel;
	}

	@Override
	public UserModel getUserById(Long user_id) {
		Optional<UserModel> userModel = userRepository.findById(user_id);
		if (userModel.isEmpty()){
			throw new UserNotFoundException("User with id " + user_id + " not found!");
			}
		else {
			return userModel.get();	
		}
	}
}
