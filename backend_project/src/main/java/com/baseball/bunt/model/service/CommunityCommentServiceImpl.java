package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dao.CommunityCommentDao;
import com.baseball.bunt.model.dto.community.CommunityBoardComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityCommentServiceImpl implements CommunityCommentService {

    private final CommunityCommentDao dao;

    @Override
    public List<CommunityBoardComment> readCommentList(int communityBoardId) {
        return dao.commentList(communityBoardId);
    }

    @Override
    public CommunityBoardComment readCommentDetail(int communityBoardCommentId) {
        return dao.commentDetail(communityBoardCommentId);
    }

    @Override
    public int createComment(CommunityBoardComment comment) {
        return dao.insertComment(comment);
    }

    @Override
    public int removeComment(int communityBoardCommentId) {
        return dao.deleteComment(communityBoardCommentId);
    }

    @Override
    public int modifyComment(CommunityBoardComment comment) {
        return dao.updateComment(comment);
    }
}
