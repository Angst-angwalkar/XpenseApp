//package io.evilsking.XpenseUser.Models;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//
//
//import javax.persistence.GenerationType;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Column;
//
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity(name="Role")
//@Table(
//		name="Role"
//		)
//public class RoleModel {
//
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="roleId", nullable=false)
//	private long roleId;
//
//	@Column(name="roleName", nullable=false, unique=true)
//	private String roleName;
//
//
//	@ManyToMany(mappedBy="roles")
//	private List<UserModel> users = new ArrayList<>();
//
//
//	public void setRoleName(String roleName) {
//		this.roleName = roleName;
//
//	}
//
//
//}
