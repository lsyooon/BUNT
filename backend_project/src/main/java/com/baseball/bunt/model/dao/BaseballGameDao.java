package com.baseball.bunt.model.dao;

import com.baseball.bunt.model.dto.game.BaseballGame;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseballGameDao {
    // 랭킹 목록
    List<BaseballGame> getBaseballGames();
    
    // 기록 추가
    int insertScore(BaseballGame baseballGame);
}
