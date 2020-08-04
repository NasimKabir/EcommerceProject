package com.nasim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Table(name = "roles")
@Data
public class Role extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false,unique = true)
	@NotNull(message = "RoleName can not empty ")
	private String name;
	
    
}
