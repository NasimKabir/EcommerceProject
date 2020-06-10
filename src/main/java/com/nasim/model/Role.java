package com.nasim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  

	@Column(nullable=false,unique = true)
	private String name;
	
	
	
    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
    
}
