package io.evilsking.ExpenseService.AdvancedExpenseSearch;

import io.evilsking.ExpenseService.AdvancedExpenseSearch.ExpenseSearchOperation;
import io.evilsking.ExpenseService.Models.ExpenseModel;
import io.evilsking.ExpenseService.Models.UserModel;
import io.evilsking.ExpenseService.AdvancedExpenseSearch.ExpenseSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class ExpenseSpecification implements Specification<ExpenseModel> {

    private final ExpenseSearchCriteria expenseSearchCriteria;

    public ExpenseSpecification(final ExpenseSearchCriteria expenseSearchCriteria){
        super();
        this.expenseSearchCriteria = expenseSearchCriteria;
    }


    private Join<UserModel, ExpenseModel> userExpenseJoin(Root<ExpenseModel> root){
        return root.join("user");
    }


    @Override
    public Predicate toPredicate(Root<ExpenseModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        String stringToSearch = expenseSearchCriteria.getValue().toString().toLowerCase();
        switch (
            Objects.requireNonNull(
                    ExpenseSearchOperation.getExpenseSearchOperation(expenseSearchCriteria.getOperation())
                )
        ){
            case CONTAINS:
                if (expenseSearchCriteria.getFilterKey().equals("userId")){
                    return criteriaBuilder.like(criteriaBuilder.lower(userExpenseJoin(root).<String>get(expenseSearchCriteria.getFilterKey())), "%" + stringToSearch + "%");
                }
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(expenseSearchCriteria.getFilterKey())), "%" + stringToSearch + "%");

            case GREATER_THAN:
                return criteriaBuilder.greaterThan(root.<String>get(expenseSearchCriteria.getFilterKey()), expenseSearchCriteria.getValue().toString());

            case NULL:
                return criteriaBuilder.isNull(root.get(expenseSearchCriteria.getFilterKey()));

            case NOT_NULL:
                return criteriaBuilder.isNotNull(root.get(expenseSearchCriteria.getFilterKey()));

            case GREATER_THAN_EQUAL:
                return criteriaBuilder.greaterThanOrEqualTo(root.<String>get(expenseSearchCriteria.getFilterKey()), expenseSearchCriteria.getValue().toString());

            case LESS_THAN:
                return criteriaBuilder.lessThan(root.<String>get(expenseSearchCriteria.getFilterKey()), expenseSearchCriteria.getValue().toString());

            case LESS_THAN_EQUAL:
                return criteriaBuilder.lessThanOrEqualTo(root.<String>get(expenseSearchCriteria.getFilterKey()), expenseSearchCriteria.getValue().toString());
        }
        return null;
    }
}
