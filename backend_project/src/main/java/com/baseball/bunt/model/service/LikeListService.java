package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dto.community.LikeList;

import java.util.List;

public interface LikeListService {
    List<LikeList> getLikeList(String userId);
    int findLike(LikeList like);
    //    int addLikeSelectKey(LikeList like);
    void removeLike(int boardId, String userId);
    void addLike(int boardId, String userId);
}
