package com.nasim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasim.model.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

}
