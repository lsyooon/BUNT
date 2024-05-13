package com.baseball.bunt.model.dto.community;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "CommunityBoardComment DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommunityBoardComment {

	private int communityBoardCommentId;
	private int communityBoardId;
	private int userId;
	private String content;
	private String regDate;
	private String modDate;
}
