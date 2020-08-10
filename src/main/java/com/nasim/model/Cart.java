package com.nasim.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Cart {

	@Id @GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")	
	private int caid;
	
	@OneToOne @JoinColumn(name="user_id")
	private User user;	

	@ElementCollection
	@JoinTable(name="Cart_Items",
			joinColumns = @JoinColumn(name="caid"))
	private Set<OrderItems> selectedItems = new HashSet<>();

}

