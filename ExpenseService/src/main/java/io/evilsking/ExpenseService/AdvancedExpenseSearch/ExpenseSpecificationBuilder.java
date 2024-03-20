package io.evilsking.ExpenseService.AdvancedExpenseSearch;

import io.evilsking.ExpenseService.Models.ExpenseModel;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ExpenseSpecificationBuilder {

    private final List<ExpenseSearchCriteria> searchParams;


    public ExpenseSpecificationBuilder(){
        this.searchParams = new ArrayList<>();
    }
    public final ExpenseSpecificationBuilder with(String key, String operation, Object value){
        searchParams.add(new ExpenseSearchCriteria(key, operation, value));
        return this;
    }

    public final ExpenseSpecificationBuilder with(ExpenseSearchCriteria expenseSearchCriteria){
        searchParams.add(expenseSearchCriteria);
        return this;
    }

    public Specification<ExpenseModel> build(){
        if(searchParams.size() == 0){
            return null;
        }

        Specification<ExpenseModel> result = new ExpenseSpecification(searchParams.get(0));

        for (int idx = 1; idx < searchParams.size(); idx++){
            ExpenseSearchCriteria expenseSearchCriteria = searchParams.get(idx);
            result = ExpenseSearchOperation.getDataOption(
                    expenseSearchCriteria.getDataOption()) == ExpenseSearchOperation.ALL ?
                    Specification.where(result).and(new ExpenseSpecification(expenseSearchCriteria)) :
                    Specification.where(result).or(new ExpenseSpecification(expenseSearchCriteria));
        }
        return result;
    }
}
