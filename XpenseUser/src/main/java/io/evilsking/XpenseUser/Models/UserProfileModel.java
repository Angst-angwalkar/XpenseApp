package io.evilsking.XpenseUser.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="UserProfile")
@Table(
        name="UserProfile"
)
public class UserProfileModel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "profileId"
    )
    private Long profileId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "userId"
    )
    private UserModel userModel;

    @Column(
            name = "monthlyCap"
    )
    private Long monthlyCap;


    @Column(
            name="sourceOfIncome"
    )
    private String sourceOfIncome;

    @Column(
            name="occupation"
    )
    private String occupation;

    @Column(
            name="monthlyIncome"
    )
    private Long monthlyIncome;

    @Column(
            name="occupationCategory"
    )
    private String occupationCategory;


}
