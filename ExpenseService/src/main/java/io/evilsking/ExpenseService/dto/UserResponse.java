package io.evilsking.ExpenseService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse extends RepresentationModel<UserResponse> {
    private String userName;
    private String firstName;
    private String middleName;
    private Boolean isActive;
    private String lastName;
    private String age;
    private String mobileNo;
    private String email;
    private String address1;
    private String address2;
    private Long monthlyCap;
    private Long monthlyIncome;
    private String occupation;
    private List<ExpenseResponse> responseList;

}
