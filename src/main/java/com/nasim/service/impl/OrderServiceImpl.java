package com.nasim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nasim.dto.OrderDto;
import com.nasim.exception.Response;
import com.nasim.exception.ResponseBuilder;
import com.nasim.model.Order;
import com.nasim.model.OrderProductItems;
import com.nasim.model.Product;
import com.nasim.repository.OrderProductItemsRepository;
import com.nasim.repository.OrderRepository;
import com.nasim.repository.ProductRepository;
import com.nasim.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final OrderProductItemsRepository orderProductRepository;
	private final ProductRepository productRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, OrderProductItemsRepository orderProductRepository,
			ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.orderProductRepository = orderProductRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Response createOrder(OrderDto orderDto, HttpServletRequest request) {
		final double[] totalPrice = { 0.0 };
		orderDto.getItems().forEach(orderProductDto -> {
			Optional<Product> product = productRepository.findById(orderProductDto.getProductId());
			double productPrice = product.get().getPrice() * orderProductDto.getProductQuantity();
			totalPrice[0] += productPrice;
		});

		Order order = new Order();
		order.setTotalPrice(String.valueOf(totalPrice[0]));
		order.setUser(orderDto.getUserId());
		order = orderRepository.save(order);

		Order orderFinal = order;
		List<OrderProductItems> orderProducts = new ArrayList<>();
		orderDto.getItems().forEach(orderProductDto -> {
			OrderProductItems orderProduct = new OrderProductItems();
			orderProduct.setOrderId(orderFinal.getId());
			orderProduct.setProductQuantity(orderProductDto.getProductQuantity());
			orderProduct.setProductId(orderProductDto.getProductId());
			orderProduct = orderProductRepository.save(orderProduct);
			orderProducts.add(orderProduct);
		});

		return ResponseBuilder.getSuccessResponse(HttpStatus.OK,
				"Order in processing. Confirm your order by complete your payment", null);
	}

	@Override
	public Response getOrderList(int page, int size) {
		List<Order> order = new ArrayList<>();
		Pageable pagination = PageRequest.of(page, size);
		Page<Order> pageList = orderRepository.findAll(pagination);
		order = pageList.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("orderList", order);
		response.put("currentPage", pageList.getNumber());
		response.put("totalItems", pageList.getTotalElements());
		response.put("orderList", pageList);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", response);
	}

	@Override
	public Response getOrderById(Long id) {
		Order order=orderRepository.findById(id).get();
		if(order !=null) {
			OrderDto orderDto=modelMapper.map(order, OrderDto.class);
			if(orderDto !=null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " retrieved Successfully", orderDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
	}

	@Override
	public Response deleteOrderById(Long id) {
		Order order=orderRepository.findById(id).get();
		if(order !=null) {
			OrderDto orderDto =modelMapper.map(order, OrderDto.class);
			if(orderDto !=null) {
				orderRepository.deleteById(id);
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, " Order Successfully  deleted");
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, " not found");
	}

	@Override
	public Response updateOrderById(Long id, OrderDto orderDto) {
		final double[] totalPrice = { 0.0 };
		orderDto.getItems().forEach(orderProductDto -> {
			Optional<Product> product = productRepository.findById(orderProductDto.getProductId());
			double productPrice = product.get().getPrice() * orderProductDto.getProductQuantity();
			totalPrice[0] += productPrice;
		});

		Order order=orderRepository.findById(id).get();
		order.setTotalPrice(String.valueOf(totalPrice[0]));
		order.setUser(orderDto.getUserId());
		order = orderRepository.save(order);

		Order orderFinal = order;
		List<OrderProductItems> orderProducts = orderProductRepository.findAllById(id);
		orderDto.getItems().forEach(orderProductDto -> {
			OrderProductItems orderProduct = new OrderProductItems();
			orderProduct.setOrderId(orderFinal.getId());
			orderProduct.setProductQuantity(orderProductDto.getProductQuantity());
			orderProduct.setProductId(orderProductDto.getProductId());
			orderProduct = orderProductRepository.save(orderProduct);
			orderProducts.add(orderProduct);
		});

		return ResponseBuilder.getSuccessResponse(HttpStatus.OK,
				"Order updating. Confirm your order by complete your payment", null);
	}

}
