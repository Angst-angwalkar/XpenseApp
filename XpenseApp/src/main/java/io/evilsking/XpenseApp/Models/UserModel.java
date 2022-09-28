package io.evilsking.XpenseApp.Models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;

@Entity(name="User")
@Table(
		name="User", 
		uniqueConstraints = {
			@UniqueConstraint(name = "mobile_unq", columnNames = {"mobileNo"}),
			@UniqueConstraint(name = "email_unq", columnNames = {"email"}),
			@UniqueConstraint(name = "aadhar_unq", columnNames = {"aadharNo"}),
			@UniqueConstraint(name = "panNo_unq", columnNames = {"panNo"})
		}
)
public class UserModel {
	
//	SequenceGenerator was used previously in HB 4 for auto incrementing with strategy as SEQUENCE, now automatically allows to increment of primary key using IDENTITY in HB 5
//	@SequenceGenerator( 
//			name = "student_id_sequence",
//			sequenceName = "student_id_sequence",
//			allocationSize = 1
//		)
	@Id
	@GeneratedValue(strategy = IDENTITY) // will also take generator="student_id_sequence" if using HB 4 along with above mentioned techniques
	private Long id;
	@Column(
		name="firstName", nullable=false
		)
	private String firstName;
	
	@Column(
		name="middleName"
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
		name="mobileNo", nullable=false
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
		name="address2", length=100
		)
	private String address2;
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
				+ "id=" + id + 
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
	

	public UserModel(Long id, String firstName, String middleName, String lastName, String age, String mobileNo,
			String email, String panNo, String aadharNo, String address1, String address2) {
		this.id = id;
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
	}
	
	public UserModel() {
		super();
	}
	
}
