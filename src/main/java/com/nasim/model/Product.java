package com.nasim.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Entity
@Table(name = "products")
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String productCode;

	@NotNull
	private String productName;

	@NotNull
	private String description;

	@NotNull
	private Double price;

	@NotNull
	private String productDetails;

	@NotNull
	private String gender;

	@OneToMany( mappedBy = "product")
	@JsonManagedReference
	private Set<Category> categories;

	private String imagePath;
}
