package io.evilsking.XpenseApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.evilsking.XpenseApp.Models.UserModel;

public interface UserRepository extends JpaRepository <UserModel, Long> {
	
	UserModel getUserById(Long id);
	
	UserModel saveUser(UserModel userModel);
}
