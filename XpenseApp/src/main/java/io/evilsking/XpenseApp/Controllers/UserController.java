package io.evilsking.XpenseApp.Controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.evilsking.XpenseApp.Exceptions.UserExceptions.UserNotFoundException;
import io.evilsking.XpenseApp.Models.UserModel;
import io.evilsking.XpenseApp.Services.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method=RequestMethod.GET, path="/all")
	public ResponseEntity<List<UserModel>> getAllUsers(){
		return new ResponseEntity<List<UserModel>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, path="/{userId}")
	public ResponseEntity<UserModel> getUserDetails(@PathVariable Long userId){
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, path="/create-user")
	public ResponseEntity<Object> createUser(@RequestBody UserModel userModel) {
		UserModel savedUser = userService.saveUser(userModel);
		System.out.println(userModel.getUserId());
		if (savedUser.getUserId() == null) {
			return new ResponseEntity<>("Could not create user!", HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		}
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
