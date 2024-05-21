package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dto.community.LikeList;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "좋아요 서비스")
public interface LikeListService {
    List<LikeList> getLikeList(String userId);
    void removeLike(int boardId, String userId);
    void addLike(int boardId, String userId);
    int find_like(String userId, int boardId);
    int likeCnt(int boardId);
}
