package com.baseball.bunt.model.dto.shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Tag(name = "찜 DTO")
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
