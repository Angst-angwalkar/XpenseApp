package io.evilsking.ExpenseService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Expenses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    private Long userId;
    private String category;
    private Long amount;
    private Long priority;



}
