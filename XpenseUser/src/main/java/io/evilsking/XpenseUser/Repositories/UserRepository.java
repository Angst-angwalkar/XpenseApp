package io.evilsking.XpenseUser.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.evilsking.XpenseUser.Models.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserModel, Long> {
	
	UserModel getUserByUserName(String userName);
	
	UserModel getUserByEmail(String email);
	
	UserModel getUserByMobileNo(String mobileNo);
	
	UserModel getUserByPanNo(String panNo);
	
	UserModel getUserByAadharNo(String aadharNo);

	Optional<UserModel> findByUserId(Long userId);
	
}
