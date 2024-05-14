package com.baseball.bunt.model.dto.shop;

import org.springframework.web.multipart.MultipartFile;

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

	private String originalImagePath;

	private String category;

	private String itemDetail;

	private String regDate;

	private String modDate;

	private MultipartFile file;
}
