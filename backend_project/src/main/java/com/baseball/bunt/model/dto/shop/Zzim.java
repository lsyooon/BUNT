package com.baseball.bunt.model.dto.shop;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Zzim DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Zzim {

	private int zzimId;
	private int userId;
	private int itemId;
	private String status;
}
