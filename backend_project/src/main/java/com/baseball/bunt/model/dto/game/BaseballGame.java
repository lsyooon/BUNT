package com.baseball.bunt.model.dto.game;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Tag(name = "숫자 야구 게임 DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseballGame {
    private String nickName;
    private int score;
    private Date regDate;
    private int rank;
}
