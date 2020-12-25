package com.nasim.service;

import javax.servlet.http.HttpServletRequest;

import com.nasim.dto.OrderDto;
import com.nasim.exception.Response;

public interface OrderService {
	Response createOrder(OrderDto orderDto, HttpServletRequest request);

	public Response getOrderList(int page, int size);

	public Response getOrderById(Long id);

	public Response deleteOrderById(Long id);

	public Response updateOrderById(Long id,OrderDto orderDto);
}
