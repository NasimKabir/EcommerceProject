package com.nasim.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Table(name = "products")
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true,nullable = false)
	private int id;

	@NotNull(message = "productCode can not empty ")
	private String productCode;

	@NotNull(message = "productName can not empty ")

	private String productName;

	@NotNull(message = "description can not empty ")

	private String description;

	@NotNull(message = "price can not empty ")

	private Double price;

	@NotNull(message = "productDetails can not empty ")

	private String productDetails;

	@NotNull(message = "Gender can not empty ")

	private String gender;

	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "products_categories", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "ID") }, 
	inverseJoinColumns = {
			@JoinColumn(name = "category_id", referencedColumnName = "ID") })
	private List<Category> categories;

	@NotNull(message = "imagePath can not empty ")

	private String imagePath;
}
