package com.nasim.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address extends BaseModel{

	private static final long serialVersionUID = 1L;
	private String phoneNumber;
	private String city;
	private String state;
	private String country;
	private String zipCode;

}
