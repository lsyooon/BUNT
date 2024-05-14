package com.baseball.bunt.model.dto.shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "상품 주문 DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItem {

	private int orderItemId;
	private int orderId;
	private int itemId;
	private int quantity;
	private int price;
}
