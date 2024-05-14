package com.baseball.bunt.model.dto.shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "상품 문의 DTO")
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
