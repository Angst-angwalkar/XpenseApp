package io.evilsking.ExpenseService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.evilsking.ExpenseService.Models.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserModel, Long> {
    Optional<UserModel> findByUserId(Long userId);

}