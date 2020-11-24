package com.nasim.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
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
}
