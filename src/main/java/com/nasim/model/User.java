package com.nasim.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Size(min=5)
	    private String username;

	    @Size(min=4)
	    //@JsonIgnore
	    private String password;

	    @NotNull
	    private String firstName;

	    @NotNull
	    private String lastName;

	    @ManyToMany(cascade = CascadeType.MERGE)
		@JoinTable(name="user_role",
		           joinColumns = {@JoinColumn(name="USER_ID",referencedColumnName = "Id")},
		           inverseJoinColumns = {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")})
	    private List<Role> roles;

	    
	    
	    
	    
	    
	    
		

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}


		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public List<Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Role> roles) {
			this.roles = roles;
		}
	    
	    
	    
}
