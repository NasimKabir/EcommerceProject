package com.nasim.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Order extends BaseModel{
private Cart cart;

}
