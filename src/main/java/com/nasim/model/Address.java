package com.nasim.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  	private String phoneNumber;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    

}
