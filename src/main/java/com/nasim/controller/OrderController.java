package com.nasim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.model.Order;
import com.nasim.repository.OrderRepository;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

	@PostMapping("/order")
	public Order createOrder(@RequestBody Order order) {
		return orderRepository.save(order);

	}
}
