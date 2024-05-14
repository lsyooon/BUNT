package com.baseball.bunt.model.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "회원")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	@Schema(description = "회원 번호")
	private int userId;
	@Schema(description = "아이디")
	private String id;
	@Schema(description = "비밀번호")
	private String password;
	@Schema(description = "이메일")
	private String email;
	@Schema(description = "이름")
	private String name;
	@Schema(description = "닉네임")
	private String nickname;
	@Schema(description = "나이")
	private int age;
	@Schema(description = "전화번호")
	private String phoneNumber;
	@Schema(description = "주소")
	private String address;
	@Schema(description = "가입날짜")
	private String joinDate;
	@Schema(description = "수정날짜")
	private String modDate;
}
