package com.nasim.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "address")
	private Order order;
}
