package com.nasim.model;

import java.beans.Transient;
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

import com.sun.istack.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    

	@OneToOne(fetch = FetchType.LAZY)
	private Address address;
	


	@OneToMany( mappedBy = "id.order",cascade = CascadeType.ALL)
	private List<OrderProductItems> items;
	
	@ManyToOne
	private User user;
	
//@Transient
	public Double getTotal(){
        double sum = 0.0;
        for (OrderProductItems x: items){
            sum += x.getSubTotal();
        }
        return sum;
    }

public Order(int id, String createdBy, Date createdAt, List<OrderProductItems> items) {
	super();
	this.id = id;
	this.createdBy = createdBy;
	this.createdAt = createdAt;
	this.items = items;
}


}
