package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Order DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

	private int orderId;
	private int userId;
	private int deliveryId;
	private String orderDate;
	private String status;
	private int totalPrice;
}
