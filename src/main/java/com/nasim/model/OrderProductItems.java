package com.nasim.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderProductItems {
	@EmbeddedId
	private OrderProductItemsPk id;

	private int quantity;


	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public Double getSubTotal() {
		return getProduct().getPrice() * quantity;
	}
}
