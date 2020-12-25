package com.nasim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasim.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
