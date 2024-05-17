package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dto.community.CommunityBoardComment;

import java.util.List;

public interface CommunityCommentService {

    // 해당 게시글 댓글 목록
    List<CommunityBoardComment> readCommentList(int communityBoardId);

    // 댓글 상세 보기
    CommunityBoardComment readCommentDetail(int communityBoardCommentId);

    // 댓글 등록
    int createComment(CommunityBoardComment comment);

    // 댓글 삭제
    int removeComment(int communityBoardCommentId);

    // 댓글 수정
    int modifyComment(CommunityBoardComment comment);

}
