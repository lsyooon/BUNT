package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "InquiryComment DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InquiryComment {

	private int inquiryCommentId;
	private int userId;
	private int boardId;
	private String content;
	private String regDate;
	private String modDate;
}
