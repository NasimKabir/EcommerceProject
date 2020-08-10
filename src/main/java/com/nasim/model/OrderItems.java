package com.nasim.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String price;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	
	private Product product;
}
