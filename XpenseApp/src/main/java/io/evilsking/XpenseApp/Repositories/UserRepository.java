package io.evilsking.XpenseApp.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.evilsking.XpenseApp.Models.UserModel;

public interface UserRepository extends JpaRepository <UserModel, Long> {
	
	UserModel getUserByUserName(String userName);
	
	UserModel getUserByEmail(String email);
	
	UserModel getUserByMobileNo(String mobileNo);
	
	UserModel getUserByPanNo(String panNo);
	
	UserModel getUserByAadharNo(String aadharNo);
	
}
