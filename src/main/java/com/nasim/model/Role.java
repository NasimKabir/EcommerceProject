package com.nasim.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Entity
@Table(name = "roles")
@Data
public class Role extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false,unique = true)
	@NotNull(message = "RoleName can not empty ")
	private String name;
	
	@JsonManagedReference
	@ManyToMany(mappedBy = "roles")
	private List<User>user;
    
}
