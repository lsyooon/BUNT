package com.baseball.bunt.model.dao;

import com.baseball.bunt.model.dto.community.Community;
import com.baseball.bunt.model.dto.community.CommunityBoardComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityCommentDao {

    // 해당 게시글 댓글 목록
    List<CommunityBoardComment> commentList(int communityBoardId);

    // 댓글 상세보기
    CommunityBoardComment commentDetail(int communityBoardCommentId);

    // 댓글 등록
    int insertComment(CommunityBoardComment comment);

    // 댓글 수정
    int updateComment(CommunityBoardComment comment);

    // 댓글 삭제
    int deleteComment(int communityBoardCommentId);

}
