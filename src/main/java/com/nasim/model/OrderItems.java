package com.nasim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int productId;
	private int productQuantity;
	private double productPrice;
	private String productName;

	@ManyToOne
	private Order order;
}
