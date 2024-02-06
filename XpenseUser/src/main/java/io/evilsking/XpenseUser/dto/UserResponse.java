package io.evilsking.XpenseUser.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long userId;
    String userName;
    String firstName;
    String middleName;
    boolean isActive;
    String lastName;
    String age;
    String mobileNo;
    String email;
    String address1;
    String address2;
    List<ExpenseResponse> responseList;

}
