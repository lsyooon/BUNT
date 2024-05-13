package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Item DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

	private int itemId;
	private int teamId;
	private String name;
	private int price;
	private int stockQuantity;
	private String imagePath;
	private String category;
	private String itemDetail;
	private String regDate;
	private String modDate;
}
