package com.nasim.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "product")
@Data
public class Product extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull(message = "productCode can not empty ")
	private String productCode;

	@NotNull(message = "productName can not empty ")
	private String productName;

	@NotNull(message = "description can not empty ")
	private String description;

	@NotNull(message = "price can not empty ")
	private double price;

	@NotNull(message = "productDetails can not empty ")
	private String productDetails;

	//@NotNull(message = "Gender can not empty ")
	private String gender;
	
	//@NotNull(message = "imagePath can not empty ")
	private String imagePath;


	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinTable(name = "product_category", joinColumns =
			@JoinColumn(name = "product_id"), 
	inverseJoinColumns = 
			@JoinColumn(name = "category_id"))
	@JsonBackReference
	@ToString.Exclude
	private List<Category> category;
	

	
	}
