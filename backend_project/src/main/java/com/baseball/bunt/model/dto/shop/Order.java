package com.baseball.bunt.model.dto.shop;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "주문 DTO")
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

	private List<OrderItem> orderItems = new ArrayList<>();
}
