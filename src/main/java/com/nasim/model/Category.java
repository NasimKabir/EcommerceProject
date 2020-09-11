package com.nasim.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseModel {

	private static final long serialVersionUID = 1L;
	@NotNull
	private String name;

	@JsonManagedReference
	@ManyToMany(mappedBy = "categories")
	private List<Product> product;

}
