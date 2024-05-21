package com.baseball.bunt.model.dto.team;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "구단 DTO")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Team {

	private int teamId;
	private String name;
}
