package com.baseball.bunt.model.dto.team;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Team DTO")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Team {

	private int teamId;
	private String name;
	private String songPath;
}
