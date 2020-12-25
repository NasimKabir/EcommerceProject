package com.nasim.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 4, max = 15)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@NotBlank
	// @Size(min = 4, max = 15)
	private String password;

	@NotBlank
	// @Size( max = 11, message = "digits should be 11")
	private String phone;
	
	@OneToMany(mappedBy = "user")
	private List<Order>  order;

	public User(String username, String email, String phone, String password) {
		super();
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

}
