package com.baseball.bunt.model.dto.community;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "CommunityBoard DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommunityBoard {

	private int communityBoardId;
	private int communityId;
	private int userId;
	private String title;
	private String content;
	private String regDate;
	private String modDate;
}
