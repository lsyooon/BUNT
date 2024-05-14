package com.baseball.bunt.model.dto.shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "장바구니 DTO")
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
