package io.evilsking.XpenseUser.Controllers;

import java.util.List;
import java.util.Locale;

import io.evilsking.XpenseUser.dto.ExpenseResponse;
import io.evilsking.XpenseUser.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.evilsking.XpenseUser.Models.UserModel;
import io.evilsking.XpenseUser.Services.UserService;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping(value="/user")
public class UserController {
	

	@Autowired
	private UserService userService;

	@Autowired
	private WebClient webClient;
	
	
	@RequestMapping(method=RequestMethod.GET, path="/all")
	public ResponseEntity<List<UserModel>> getAllUsers(){
		return new ResponseEntity<List<UserModel>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, path="/{userId}")
	public ResponseEntity<UserResponse> getUserDetails(@PathVariable Long userId){

		UserResponse userResponse = userService.getUserById(userId);
		
		return ResponseEntity.ok(userResponse);
		
//		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, path="/create-user")
	public ResponseEntity<String> createUser(
			@RequestBody UserModel userModel,
			@RequestHeader(value = "Accept-Language", required=false) Locale locale
			) {
		return ResponseEntity.ok(userService.saveUser(userModel, locale));

	}
	
	
	@RequestMapping(method=RequestMethod.PUT, path="/update-user/{userId}")
	public ResponseEntity<Object> updateUser(@RequestBody UserModel userModel, @PathVariable Long userId){
		UserModel updateUser = userService.updateUserDetails(userModel, userId);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value="/delete/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long userId){
		boolean del = userService.deleteUser(userId);
		return new ResponseEntity<>(del, HttpStatus.OK);
	}
	
	

}
