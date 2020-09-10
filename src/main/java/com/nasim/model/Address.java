package com.nasim.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address extends BaseModel{

	private static final long serialVersionUID = 1L;
	private String phoneNumber;
	private String city;
	private String state;
	private String country;
	private String zipCode;

	@OneToOne(fetch = FetchType.LAZY,mappedBy = "address")
	private Order order;
}
