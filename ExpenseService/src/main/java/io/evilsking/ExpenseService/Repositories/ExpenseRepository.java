package io.evilsking.ExpenseService.Repositories;


import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.dto.ExpenseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {

    Optional<ExpenseModel> findByExpenseId(Long expenseId);

    List<ExpenseModel> findByUserId(Long userId);
}
