package com.nasim.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.mapping.Set;

import gd.rf.thiagolemes.coursejava.entities.HashSet;
import gd.rf.thiagolemes.coursejava.entities.Order;
import gd.rf.thiagolemes.coursejava.entities.OrderItem;
import lombok.Data;


@Entity
@Table(name = "products")
@Data
public class Product extends BaseModel implements Serializable{


	@NotNull(message = "productCode can not empty ")
	private String productCode;

	@NotNull(message = "productName can not empty ")
	private String productName;

	@NotNull(message = "description can not empty ")
	private String description;

	@NotNull(message = "price can not empty ")
	private Double price;

	@NotNull(message = "productDetails can not empty ")
	private String productDetails;

	@NotNull(message = "Gender can not empty ")
	private String gender;
	
	@NotNull(message = "imagePath can not empty ")
	private String imagePath;


	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "products_categories", joinColumns =
			@JoinColumn(name = "product_id"), 
	inverseJoinColumns = 
			@JoinColumn(name = "category_id"))
	private List<Category> categories;
	
	@OneToMany(mappedBy = "product")
	private List<OrderItems> orderitems;

	public List<Order> getOrders(){
        Set<Order> set = new HashSet<>();
        for(OrderItem x : items){
            set.add(x.getOrder());
        }
        return set;
    }
	}
