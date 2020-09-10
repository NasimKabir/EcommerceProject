package com.nasim.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @Column(updatable = false)
    private String createdBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;
    
	private double totalPrice;

	@OneToOne(fetch = FetchType.LAZY)
	private Address address;
	
	
	@OneToMany( mappedBy = "order")
	private List<OrderItems> items;
	
	@ManyToOne
	private User user;

}
