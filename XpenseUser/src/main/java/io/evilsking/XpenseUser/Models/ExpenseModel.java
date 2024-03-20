package io.evilsking.XpenseUser.Models;

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
public class ExpenseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

    private String userName;
    private String category;
    private Long amount;
    private Long priority;
    private Long monthlyLimit;
    private Boolean isActive;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date createdOn;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date expenseDate;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedOn;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date deletedOn;

}
