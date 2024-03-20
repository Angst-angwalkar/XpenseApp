package io.evilsking.ExpenseService.AdvancedExpenseSearch;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseSearchCriteria {
    private String filterKey;
    private Object value;
    private String operation;
    private String dataOption;

    public ExpenseSearchCriteria(String filterKey, String operation, Object value){
        super();
        this.filterKey = filterKey;
        this.value = value;
        this.operation = operation;
    }
}
