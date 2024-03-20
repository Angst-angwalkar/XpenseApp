package io.evilsking.ExpenseService.Repositories;


import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.dto.ExpenseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long>, JpaSpecificationExecutor<ExpenseModel> {

    Optional<ExpenseModel> findByExpenseId(Long expenseId);

    @Query("SELECT expense from ExpenseModel as expense where expense.user.userId = :userId")
    List<ExpenseModel> findByUserId(Long userId);
}
