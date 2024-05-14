package com.baseball.bunt.model.dto.community;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "커뮤니티 DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Community {

	private int communityId;
	private int teamId;
	private String name;
	private String description;
}
