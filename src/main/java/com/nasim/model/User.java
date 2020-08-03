package com.nasim.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User extends BaseModel{
	
	private static final long serialVersionUID = 1L;

	@Size(min=4,message = "Username must be atleast 4 characters")
	private String username;

	 @Size(min=4,message = "Password must be atleast 4 ")
	//@JsonIgnore
	private String password;

	@NotNull(message = "Firstname can not empty ")
	private String firstName;

	@NotNull(message = "Lastname can not empty ")
	private String lastName;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "Id") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	private List<Role> roles;

}
