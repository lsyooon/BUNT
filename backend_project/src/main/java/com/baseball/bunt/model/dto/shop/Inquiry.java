package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Inquiry DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inquiry {

	private int inquiryId;
	private int userId;
	private int itemId;
	private String title;
	private String content;
	private String regDate;
	private String modDate;
}
