package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dto.game.BaseballGame;

import java.util.List;

public interface BaseballGameService {
    // 랭킹 목록
    List<BaseballGame> getRanking();

    // 기록 추가
    int addScore(BaseballGame baseballGame);
}
