package io.evilsking.XpenseApp.RepositoryImpl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import io.evilsking.XpenseApp.Models.UserModel;
import io.evilsking.XpenseApp.Repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private EntityManager entityManager;
	
	public UserRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public UserModel getUserById(Long id) {
		return entityManager.find(UserModel.class, id);
	}
	
	@Override
	public UserModel saveUser(UserModel userModel) {
		if (userModel.getId() == null) {
			entityManager.persist(userModel);
		}
		else {
			userModel = entityManager.merge(userModel);
		}
		return userModel;
	}
	
}
