package io.evilsking.ExpenseService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    private Boolean isActiveUser;
    private Long monthlyLimit;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdOn;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updatedOn;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date deletedOn;



}
