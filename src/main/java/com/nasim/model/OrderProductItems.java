package com.nasim.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="orderProductItems")
public class OrderProductItems {
	@EmbeddedId
	private OrderProductItemsPk id;

	private int quantity;

//@Transient
	public Product getProduct() {
		return id.getProduct();
	}

//	@Transient
	public Double getSubTotal() {
		return getProduct().getPrice() * quantity;
	}
}
