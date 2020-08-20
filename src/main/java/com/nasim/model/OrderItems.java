package com.nasim.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int productId;
	private int productQuantity;
	private double productPrice;
	private String productName;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
