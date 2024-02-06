package io.evilsking.ExpenseService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse {
    private Long expenseId;
    private Long userId;
    private String category;
    private Long amount;
    private Long priority;

}
