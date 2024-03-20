package io.evilsking.ExpenseService.Models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="User")
@Table(
        name="User"
)
public class UserModel extends RepresentationModel<UserModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "userId", nullable = false
    )
    private Long userId;


    @Column(
            name = "userName", nullable = false, length = 25
    )
    private String userName;

    @Column(
            name = "firstName", nullable = false
    )
    private String firstName;


    @Column(
            name = "middleName", nullable = true
    )
    private String middleName;

    @Column(
            name = "lastName", nullable = false
    )
    private String lastName;

    @Column(
            name = "age", nullable = false
    )
    private String age;

    @Column(
            name = "mobileNo", nullable = false, length = 25
    )
    private String mobileNo;


    @Column(
            name = "isActive",
            columnDefinition = "boolean default false"
    )
    private Boolean isActive;

    @Column(
            name = "email", nullable = false, length = 25
    )
    private String email;


    @Column(
            name = "address1", nullable = false, length = 100
    )
    private String address1;

    @Column(
            name = "address2", nullable = true, length = 100
    )
    private String address2;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ExpenseModel> expenseModelList;





}