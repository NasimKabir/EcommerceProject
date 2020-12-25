package com.nasim.dto;

import java.util.List;

import com.nasim.model.OrderProductItems;
import com.nasim.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private User userId;
	private String totalPrice;
	private List<OrderProductItems> items;
}
