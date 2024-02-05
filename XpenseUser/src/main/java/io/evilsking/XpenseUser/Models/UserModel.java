package io.evilsking.XpenseUser.Models;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY) // will also take generator="student_id_sequence" if using HB 4 along with above mentioned techniques
	@Column(
		name="userId", nullable=false
		)
	private Long userId;
	
	
	@Column(
		name="userName", nullable=false, length=25
		)
	private String userName;
	
	@Column(
		name="firstName", nullable=false
		)
	private String firstName;
	
	
	@Column(
		name="middleName", nullable=true
		)
	private String middleName;
	
	@Column(
		name="lastName", nullable=false
		)
	private String lastName;
	
	@Column(
		name="age", nullable=false
		)
	private String age;
	
	@Column(
		name="mobileNo", nullable=false, length=25
		)
	private String mobileNo;
	
	@Column(
		name="email", nullable=false, length=25
		)
	private String email;
	
	@Column(
		name="panNo", nullable=false,  length=10
		)
	private String panNo;
	
	@Column(
		name="aadharNo", nullable=false,  length=12
		)
	private String aadharNo;
	
	
	@Column(
		name="address1", nullable=false, length=100
		)
	private String address1;
	
	@Column(
		name="address2", nullable=true,length=100
		)
	private String address2;
	
	
	@Column(nullable=false)
    private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name="user_roles",
			joinColumns= {@JoinColumn(name="USERMODEL_ID", referencedColumnName="userId")},
			inverseJoinColumns= {@JoinColumn(name="ROLEMODEL_ID", referencedColumnName="roleId")}
			)
	private List<RoleModel> roles = new ArrayList<>();
	
	

	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPanNo() {
		return panNo;
	}
	
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	
	public String getAadharNo() {
		return aadharNo;
	}
	
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getAddress1() {
		return address1;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@Override
	public String toString() {
		return "UserModel "+
				"{"
				+ "user_id=" + userId + 
				", userName=" + userName +
				", firstName=" + firstName + 
				", middleName=" + middleName + 
				", lastName=" + lastName + 
				", age=" + age + 
				", mobileNo=" + mobileNo + 
				", email=" + email + 
				", panNo=" + panNo + 
				", aadharNo=" + aadharNo + 
				", address1=" + address1 + 
				", address2=" + address2 + 
				"}";
	}
	

	public UserModel(String userName, String firstName, String middleName, String lastName, String age, String mobileNo,
			String email, String panNo, String aadharNo, String address1, String address2, String roles, String password) {
		this.userName = userName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.age = age;
		this.mobileNo = mobileNo;
		this.email = email;
		this.panNo = panNo;
		this.aadharNo = aadharNo;
		this.address1 = address1;
		this.address2 = address2;
		this.password = password;
	}
	
	public UserModel() {
		super();
	}
	
}
