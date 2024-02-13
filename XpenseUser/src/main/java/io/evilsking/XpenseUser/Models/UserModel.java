package io.evilsking.XpenseUser.Models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.evilsking.XpenseUser.dto.ExpenseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="User")
@Table(
		name="User", 
		uniqueConstraints = {
			@UniqueConstraint(name = "username_unq", columnNames = {"userName"}),
			@UniqueConstraint(name = "mobile_unq", columnNames = {"mobileNo"}),
			@UniqueConstraint(name = "email_unq", columnNames = {"email"}),
			@UniqueConstraint(name = "aadhar_unq", columnNames = {"aadharNo"}),
			@UniqueConstraint(name = "panNo_unq", columnNames = {"panNo"})
		}
)
public class UserModel extends RepresentationModel<UserModel> {

	//	SequenceGenerator was used previously in HB 4 for auto incrementing with strategy as SEQUENCE, now automatically allows to increment of primary key using IDENTITY in HB 5
	//	@SequenceGenerator(
	//			name = "student_id_sequence",
	//			sequenceName = "student_id_sequence",
	//			allocationSize = 1
	//		)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// will also take generator="student_id_sequence" if using HB 4 along with above mentioned techniques
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
			name = "panNo", nullable = false, length = 10
	)
	private String panNo;

	@Column(
			name = "aadharNo", nullable = false, length = 12
	)
	private String aadharNo;


	@Column(
			name = "address1", nullable = false, length = 100
	)
	private String address1;

	@Column(
			name = "address2", nullable = true, length = 100
	)
	private String address2;


	@Column(nullable = false)
	private String password;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(
			name = "profileId"
	)
	@JsonIgnore
	private UserProfileModel userProfileModel;

	@Column(
			name = "createdOn",
			nullable = false
	)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdOn;

	@Column(
			name = "updatedOn"
	)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date updatedOn;

	@Column(
			name = "deactivatedOn"
	)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date deactivatedOn;




}