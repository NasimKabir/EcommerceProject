package com.nasim.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.nasim.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends RepresentationModel<ProductDto>{
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

	@NotNull(message = "Gender can not empty ")
	private String gender;
	
	@NotNull(message = "imagePath can not empty ")
	private String imagePath;

	private List<Category> categories;
	
	
}
