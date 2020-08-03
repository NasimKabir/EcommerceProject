package com.nasim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "roles")
@Data
public class Role extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false,unique = true)
	private String name;
	
    
}
