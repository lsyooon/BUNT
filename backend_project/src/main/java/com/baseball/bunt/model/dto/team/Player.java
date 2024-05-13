package com.baseball.bunt.model.dto.team;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Player DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player {

	private int playerId;
	private int teamId;
	private String name;
	private int age;
	private int backNumber;
	private String songPath;
}
