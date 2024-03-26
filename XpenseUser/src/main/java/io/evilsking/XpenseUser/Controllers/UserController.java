package io.evilsking.XpenseUser.Controllers;

import java.util.List;
import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.evilsking.XpenseUser.dto.UserResponse;
import io.evilsking.XpenseUser.Models.UserModel;
import io.evilsking.XpenseUser.Services.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
	

	@Autowired
	private UserService userService;


	@RequestMapping(method=RequestMethod.GET, path="/all")
	public ResponseEntity<List<UserModel>> getAllUsers(){
		return new ResponseEntity<List<UserModel>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	

	@RequestMapping(method=RequestMethod.GET, path="/get/{userId}")
	public ResponseEntity<UserResponse> getUserDetails(@PathVariable Long userId){
		return new ResponseEntity<UserResponse>(userService.getUserById(userId), HttpStatus.OK);
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
