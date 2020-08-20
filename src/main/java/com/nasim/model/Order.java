package com.nasim.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Order extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int orderId;

	private double orderTotalPrice;

	@OneToOne
	@JoinColumn(name = "addressId")
	private Address address;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItems> OrderItems;


}
