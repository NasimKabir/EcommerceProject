package com.nasim.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class OrderProductItems extends BaseModel {
	private static final long serialVersionUID = 1L;

	private Long productId;
	private Long orderId;

	private int productQuantity;

}
