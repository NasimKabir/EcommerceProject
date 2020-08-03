package com.nasim.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Table(name = "categories")
@Data
public class Category extends BaseModel{

	private static final long serialVersionUID = 1L;
	@NotNull
	private String name;
	
	

}
