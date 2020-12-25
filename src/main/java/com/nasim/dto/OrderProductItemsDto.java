package com.nasim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductItemsDto {
	private Long productId;
    private Long orderId;
	private int productQuantity;
}
