package io.evilsking.ExpenseService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyExpenseResponse {
    private Long userId;
    private List<String> categoryList;
    private Long totalAmount;
    private Date startDate;
    private Date endDate;

}
