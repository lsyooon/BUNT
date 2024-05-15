package com.baseball.bunt.model.dto.shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "배달 DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Delivery {

	private int deliveryId;

	private Order order;

	private String status;

	private String city;

	private String street;

	private String zipcode;

	private String deliveryRequest;
}
