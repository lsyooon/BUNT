package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "OrderItem DTO")
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
