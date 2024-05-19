package com.baseball.bunt.model.dto.community;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "커뮤니티 게시글 댓글 DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommunityBoardComment {

	private int communityBoardCommentId;
	private int communityBoardId;
	private String userId;
	private String content;
	private String regDate;
	private String modDate;

	@Override
	public String toString() {
		return "CommunityBoardComment{" +
				"communityBoardCommentId=" + communityBoardCommentId +
				", communityBoardId=" + communityBoardId +
				", userId='" + userId + '\'' +
				", content='" + content + '\'' +
				", regDate='" + regDate + '\'' +
				", modDate='" + modDate + '\'' +
				'}';
	}
}
