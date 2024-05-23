package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dao.BaseballGameDao;
import com.baseball.bunt.model.dto.game.BaseballGame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BaseballGameServiceImpl implements BaseballGameService {

    private final BaseballGameDao dao;

    @Override
    public List<BaseballGame> getRanking() {
        return dao.getBaseballGames();
    }

    @Override
    public int addScore(BaseballGame baseballGame) {
        return dao.insertScore(baseballGame);
    }
}
