package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Cart DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {

	private int cartId;
	private int userId;
	private int itemId;
	private int quantity;
	private String orderStatus;
	private String regDate;
}
