package io.evilsking.ExpenseService.AdvancedExpenseSearch;

import io.evilsking.ExpenseService.AdvancedExpenseSearch.ExpenseSearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseSearchDto {
    private List<ExpenseSearchCriteria> expenseSearchCriteriaList;
    private String dataOption;

}
