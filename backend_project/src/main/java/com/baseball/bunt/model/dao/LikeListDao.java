package com.baseball.bunt.model.dao;

import com.baseball.bunt.model.dto.community.LikeList;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LikeListDao {

    List<LikeList> getLikeList(String userId);

    void removeLike(int boardId, String userId);

    int find_like(String userId, int boardId);

    void addLike(int boardId, String userId);

    int likeCnt(int boardId);
}
