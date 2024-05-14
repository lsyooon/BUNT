package com.baseball.bunt.model.dto.community;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "커뮤니티 게시글 DTO")
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
