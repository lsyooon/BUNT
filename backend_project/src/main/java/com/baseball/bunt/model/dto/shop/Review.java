package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Review DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {

	private int reviewId;
	private int userId;
	private int itemId;
	private String title;
	private String comment;
	private String imagePath;
	private String regDate;
	private String modDate;
}
