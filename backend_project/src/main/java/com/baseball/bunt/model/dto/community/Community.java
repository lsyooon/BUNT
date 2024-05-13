package com.baseball.bunt.model.dto.community;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Community DTO")
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
