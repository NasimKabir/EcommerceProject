package com.nasim.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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
	@NotNull(message = "Email can not empty ")
	private String email;

	//@JsonBackReference
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = 
			@JoinColumn(name = "USER_ID") , inverseJoinColumns = 
					@JoinColumn(name = "ROLE_ID"))
	private List<Role> roles;
	

	@OneToMany(mappedBy = "user")
	private List<Order>  order;

}
