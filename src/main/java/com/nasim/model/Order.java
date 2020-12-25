package com.nasim.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@Data
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createdAt;

	private String totalPrice;

	@OneToOne(fetch = FetchType.LAZY)
	private Address address;

	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
	private List<OrderProductItems> items;

	@ManyToOne(cascade = CascadeType.MERGE)
	private User user;

}
