package com.baseball.bunt.model.dao;

import com.baseball.bunt.model.dto.community.Criteria;
import com.baseball.bunt.model.dto.community.LikeList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LikeListDao {
    public void addLike(LikeList vo);
    public void addLikeSelectKey(LikeList vo);

    public LikeList listLike(Long lno);

    public List<LikeList> getLikeList(String userid);

    public int removeLike(@Param("mno") Long mno, @Param("userid") String userid);

    public List<LikeList> getListWithPaging(
            @Param("cri") Criteria cri,
            @Param("lno") Long lno);

    public int find_like(@Param("mno") Long mno, @Param("userid") String userid);

    public int findLike(LikeList vo);

    public void addLike(@Param("mno") Long mno, @Param("userid") String userid);
}
