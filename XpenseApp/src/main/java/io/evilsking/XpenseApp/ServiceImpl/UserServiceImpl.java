package io.evilsking.XpenseApp.ServiceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import io.evilsking.XpenseApp.Exceptions.UserExceptions.UserValidationException;
import io.evilsking.XpenseApp.Exceptions.UserExceptions.UserAlreadyExistsException;
import io.evilsking.XpenseApp.Exceptions.UserExceptions.UserNotFoundException;
import io.evilsking.XpenseApp.Models.UserModel;
import io.evilsking.XpenseApp.Repositories.UserRepository;
import io.evilsking.XpenseApp.Services.UserService;
import io.evilsking.XpenseApp.Validators.UserValidators;

@Repository
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	
	@Override
	public UserModel getUserByUserName(String userName) {
		UserModel userModel = userRepository.getUserByUserName(userName);
		return userModel;
	}
	
	@Override
	public UserModel saveUser(UserModel userModel) {
		UserModel userModel1;
		userModel1 = userRepository.getUserByUserName(userModel.getUserName());
		if (userModel1 != null){
			throw new UserAlreadyExistsException("User with username " + userModel.getUserName() + " already exists. Please choose a different username.");
		}
		
		userModel1 = userRepository.getUserByEmail(userModel.getEmail());
		if (userModel1 != null){
			throw new UserAlreadyExistsException("User with email " + userModel.getEmail() + " already exists. Please choose a different email or login to the existing account.");
		}
		
		userModel1 = userRepository.getUserByMobileNo(userModel.getMobileNo());
		if (userModel1 != null){
			throw new UserAlreadyExistsException("User with mobile no. " + userModel.getMobileNo() + " already exists. Please choose a different mobile or login to the existing account.");
		}
		
		userModel1 = userRepository.getUserByAadharNo(userModel.getAadharNo());
		if (userModel1 != null){
			throw new UserAlreadyExistsException("User with Aadhar No. " + userModel.getAadharNo() + " already exists.");
		}
		
		userModel1 = userRepository.getUserByPanNo(userModel.getPanNo());
		if (userModel1 != null){
			throw new UserAlreadyExistsException("User with PAN " + userModel.getPanNo() + " already exists.");
		}
		
		UserValidators validator = new UserValidators();
		String userNameValidation = validator.userNameValidator(userModel);
		if (userNameValidation != "OK") {
			throw new UserValidationException(userNameValidation);
		}
		
		String emailValidation = validator.emailValidator(userModel);
		if (emailValidation != "OK") {
			throw new UserValidationException(emailValidation);
		}
		
		String panValidation = validator.panValidator(userModel);
		if (panValidation != "OK") {
			throw new UserValidationException(panValidation);
		}
		
		String aadharValidation = validator.aadharValidator(userModel);
		if (aadharValidation != "OK") {
			throw new UserValidationException(aadharValidation);
		}
		
		
		String mobileValidation = validator.mobileValidator(userModel);
		if (mobileValidation != "OK") {
				throw new UserValidationException(mobileValidation);
			}
		
		userRepository.save(userModel);
		return userModel;
	
	}
	
	
	@Override
	public UserModel getUserById(Long userId) {
		Optional<UserModel> userModel = userRepository.findById(userId);
		if (userModel.isEmpty()){
			throw new UserNotFoundException("User with id " + userId + " not found!");
			}
		else {
			return userModel.get();	
		}
	}
	
	
	@Override
	public List<UserModel> getAllUsers(){
		List<UserModel> userList = userRepository.findAll();
		return userList;
	}
	
	
	
	@Override
	public UserModel updateUserDetails(UserModel userModel, Long userId) {
		Optional<UserModel> updateUser = userRepository.findById(userId);
		if (updateUser.isEmpty()) {
			throw new UserNotFoundException("Could not find user to update user details!");
		}
		else {
			userModel.setUserId(userId);
			userRepository.save(userModel);
			return userModel;
		}
	}
	
	@Override
	public boolean deleteUser(Long userId) {
		userRepository.deleteById(userId);
		return true;
	}


	@Override
	public UserModel getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserModel getUserByMobileNo(String mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserModel getUserByAadharNo(String aadharNo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserModel getUserByPanNo(String panNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
