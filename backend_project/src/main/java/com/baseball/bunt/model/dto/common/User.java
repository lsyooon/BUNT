package com.baseball.bunt.model.dto.common;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "User DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	private int userId;
	private String id;
	private String password;
	private String email;
	private String name;
	private String nickname;
	private int age;
	private String phoneNumber;
	private String address;
	private String joinDate;
	private String modDate;
}
