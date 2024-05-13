package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Delivery DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Delivery {

	private int deliveryId;
	private String status;
	private String city;
	private String street;
	private String zipcode;
	private String deliveryRequest;
}
