package io.evilsking.XpenseUser.Services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import io.evilsking.XpenseUser.Controllers.UserController;
import io.evilsking.XpenseUser.Exceptions.UserExceptions.UserAlreadyExistsException;
import io.evilsking.XpenseUser.Exceptions.UserExceptions.UserNotFoundException;
import io.evilsking.XpenseUser.Exceptions.UserExceptions.UserValidationException;
import io.evilsking.XpenseUser.Helpers.Helpers;
import io.evilsking.XpenseUser.Models.UserProfileModel;
import io.evilsking.XpenseUser.Repositories.UserProfileRepository;
import io.evilsking.XpenseUser.Repositories.UserRepository;
import io.evilsking.XpenseUser.Validators.UserValidators;
import io.evilsking.XpenseUser.dto.ExpenseResponse;
import io.evilsking.XpenseUser.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import io.evilsking.XpenseUser.Models.UserModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private WebClient.Builder loadBalancedWebClientBuilder;

	@Autowired
	MessageSource messages;

	public String saveUser(UserModel userModel, Locale locale) {
		UserModel userModel1;

		Helpers helpers = new Helpers();

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

		userModel.setCreatedOn(helpers.getCurrentDate());

		UserProfileModel userProfileModel = new UserProfileModel();

		userProfileModel.setMonthlyCap(0L);
		userProfileModel.setOccupation("");
		userProfileModel.setMonthlyIncome(0L);
		userProfileModel.setOccupationCategory("");
		userProfileModel.setUserModel(userModel);
		userProfileModel.setSourceOfIncome("");


		userProfileRepository.save(userProfileModel);
		userModel.setUserProfileModel(userProfileModel);
		userRepository.save(userModel);


		String responseMessage = null;

		if (userModel != null){
			responseMessage = String.format(messages.getMessage("user.create.message", null, locale),
					userModel.getUserName());
		}
		return responseMessage;

	}

	public UserResponse getUserById(Long userId) {
		Optional<UserModel> userModel = userRepository.findByUserId(userId);
		if (userModel.isEmpty()){
			throw new UserNotFoundException("User with id " + userId + " not found!");
		}


		else {
			Optional<UserProfileModel> userProfileModel = userProfileRepository.findByProfileId(userModel.get().getUserProfileModel().getProfileId());
			ExpenseResponse[] expenseResponses = loadBalancedWebClientBuilder.build().get().uri(
					"http://expense-service/api/expense/{userId}/details", userId)
					.retrieve()
					.bodyToMono(ExpenseResponse[].class)
					.block();


			Arrays.stream(expenseResponses).collect(Collectors.toList());



			UserResponse userResponse =  UserResponse.builder()
					.userName(userModel.get().getUserName())
					.firstName(userModel.get().getFirstName())
					.lastName(userModel.get().getLastName())
					.email(userModel.get().getEmail())
					.mobileNo(userModel.get().getMobileNo())
					.age(userModel.get().getAge())
					.isActive(userModel.get().getIsActive())
					.address1(userModel.get().getAddress1())
					.address2(userModel.get().getAddress2())
					.responseList(Arrays.stream(expenseResponses)
							.collect(Collectors.toList()))
					.build();



			userResponse.add(linkTo(methodOn(UserController.class)
							.getUserDetails(userModel.get().getUserId()))
							.withSelfRel(),
					linkTo(methodOn(UserController.class)
							.createUser(userModel.get(), null))
							.withRel("createUser"),
					linkTo(methodOn(UserController.class)
							.updateUser(userModel.get(), userModel.get().getUserId()))
							.withRel("updateUser"),
					linkTo(methodOn(UserController.class)
							.deleteUser(userModel.get().getUserId()))
							.withRel("deleteUser"));

			if (!userProfileModel.isEmpty()){
				userResponse.setOccupation(userProfileModel.get().getOccupation());
				userResponse.setMonthlyIncome(userProfileModel.get().getMonthlyIncome());
				userResponse.setMonthlyCap(userProfileModel.get().getMonthlyCap());
			}

			return userResponse;
//			return userModel.get();
		}
	}

	public UserModel updateUserDetails(UserModel userModel, Long userId) {
		Optional<UserModel> updateUser = userRepository.findById(userId);
		if (updateUser.isEmpty()) {
			throw new UserNotFoundException("Could not find user to update user details!");
		}
		else {
			Helpers helpers = new Helpers();
			userModel.setUserId(userId);
			userModel.setUpdatedOn(helpers.getCurrentDate());
			userRepository.save(userModel);
			return userModel;
		}
	}

	public boolean deleteUser(Long userId) {
//		userRepository.deleteById(userId);
		Optional<UserModel> userModel = userRepository.findByUserId(userId);

		if (userModel.isEmpty()){
			throw new UserNotFoundException("The user you're trying to delete is not found!");
		}
		else{
			Helpers helpers = new Helpers();
			userModel.get().setDeactivatedOn(helpers.getCurrentDate());
			userModel.get().setIsActive(false);
		}
		return true;
	}

	public UserModel getUserByUserName(String userName) {
		UserModel userModel = userRepository.getUserByUserName(userName);
		return userModel;
	}


	public UserModel getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserModel getUserByMobileNo(String mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserModel getUserByAadharNo(String aadharNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserModel getUserByPanNo(String panNo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserModel> getAllUsers(){
        return userRepository.findAll();
	}

	public Optional<UserProfileModel> getUserProfile(Long profileId) {
		Optional<UserProfileModel> userProfileModel = userProfileRepository.findByProfileId(profileId);
		if (userProfileModel.isEmpty()){
			throw new UserNotFoundException("User with profile id " + profileId + " not found!");
		}
		return userProfileRepository.findByProfileId(profileId);
	}

	public UserProfileModel updateUserProfile(UserProfileModel userProfileModel, Long profileId) {

		Optional<UserProfileModel> userProfileModel1 = userProfileRepository.findByProfileId(profileId);
		if (userProfileModel1.isEmpty()) {
			throw new UserNotFoundException("Could not find user to update user details!");
		}
		else {
			userProfileModel.setProfileId(profileId);
			userProfileRepository.save(userProfileModel);
			return userProfileModel;
		}
	}
}
