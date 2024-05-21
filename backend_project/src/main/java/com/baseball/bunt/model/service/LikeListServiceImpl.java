package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dao.LikeListDao;
import com.baseball.bunt.model.dto.community.LikeList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeListServiceImpl implements LikeListService{

    private final LikeListDao dao;

    @Override
    public List<LikeList> getLikeList(String userId) {
        return dao.getLikeList(userId);
    }

    @Override
    public void removeLike(int boardId, String userId) {
        dao.removeLike(boardId, userId);
    }

    @Override
    public void addLike(int boardId, String userId) {
        dao.addLike(boardId, userId);
    }

    @Override
    public int find_like(String userId, int boardId) {
        return dao.find_like(userId, boardId);
    }

    @Override
    public int likeCnt(int boardId) {
        return dao.likeCnt(boardId);
    }
}
