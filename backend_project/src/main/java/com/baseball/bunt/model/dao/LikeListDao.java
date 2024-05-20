package com.baseball.bunt.model.dao;

import com.baseball.bunt.model.dto.community.LikeList;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LikeListDao {
    public void addLike(LikeList like);
    public void addLikeSelectKey(LikeList like);

    public List<LikeList> getLikeList(String userId);

    public int removeLike(int boardId, String userId);

    public int find_like(int boardId, String userId);

    public int findLike(LikeList like);

    public void addLike(int boardId, String userId);
}
