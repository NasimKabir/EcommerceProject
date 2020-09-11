package com.nasim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int productQuantity;
	private double productPrice;

	@JsonBackReference
	@ManyToOne
	private Order order;
	
	@JsonBackReference
	@ManyToOne
	private Product product;
}
