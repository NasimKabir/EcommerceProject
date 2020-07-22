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

	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "products_categories", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "ID") }, 
	inverseJoinColumns = {
			@JoinColumn(name = "category_id", referencedColumnName = "ID") })
	private List<Category> categories;

	private String imagePath;
}
