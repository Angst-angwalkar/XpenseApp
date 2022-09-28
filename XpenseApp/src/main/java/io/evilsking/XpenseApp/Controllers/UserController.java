package io.evilsking.XpenseApp.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.evilsking.XpenseApp.Models.UserModel;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String createUser(UserModel userModel) {
		return "";
	}

}
