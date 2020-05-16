package com.nasim.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Size(min=4,message = "Username must be atleast 4 characters")
	    private String username;

	    @Size(min=4,message = "Password must be atleast 4 ")
	    @JsonIgnore
	    private String password;

	    @NotNull(message = "Firstname can not empty ")
	    private String firstName;

	    @NotNull(message = "Lastname can not empty ")
	    private String lastName;

	    @ManyToMany(cascade = CascadeType.MERGE)
		@JoinTable(name="user_role",
		           joinColumns = {@JoinColumn(name="USER_ID",referencedColumnName = "Id")},
		           inverseJoinColumns = {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")})
	    private List<Role> roles;
	    
	    @NotEmpty(message = "Date can't empty")
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date createdDate;
	    
	    @NotEmpty(message = "Date can't empty")
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date updatedDate;

	    
	    
	    
	    
	    
	    
		

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

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public void setId(int id) {
			this.id = id;
		}

		
}
