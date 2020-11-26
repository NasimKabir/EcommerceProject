package com.nasim.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.nasim.controller.ProductController;
import com.nasim.controller.UserController;
import com.nasim.dto.ProductDto;
import com.nasim.dto.UserDto;
import com.nasim.model.Product;
import com.nasim.model.User;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product,ProductDto>{

	@Override
	public ProductDto toModel(Product entity) {
		ProductDto product=new ProductDto(entity.getProductCode(),entity.getDescription(),entity.getProductDetails(),entity.getPrice(),entity.getGender(),entity.getProductName());
		product.add(linkTo(methodOn(ProductController.class).getProduct(entity.getId())).withSelfRel());
		return product;
	}

}
