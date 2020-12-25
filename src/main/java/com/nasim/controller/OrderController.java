package com.nasim.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.dto.OrderDto;
import com.nasim.exception.Response;
import com.nasim.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public Response getOrder(@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "9") Integer size) {
		return orderService.getOrderList(page, size);
	}

	@PostMapping
	public Response createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result,
			HttpServletRequest request) {
		return orderService.createOrder(orderDto, request);
	}

	@GetMapping("/{id}")
	public Response getOrderById(@PathVariable("id") Long id) {
		return orderService.getOrderById(id);
	}

	@DeleteMapping("/{id}")
	public Response deleteOrderById(@PathVariable("id") Long id) {
		return orderService.deleteOrderById(id);
	}

	@PutMapping("/{id}")
	public Response updateOrderById(@PathVariable("id") Long id, OrderDto orderDto) {
		return orderService.updateOrderById(id, orderDto);
	}

}
