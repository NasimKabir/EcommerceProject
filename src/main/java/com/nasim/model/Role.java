package com.nasim.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false,unique = true)
	@NotNull(message = "RoleName can not empty ")
	private String name;
	

	@ManyToMany(mappedBy = "roles")
	private List<User>user;
    
}
