package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dao.CommunityDao;
import com.baseball.bunt.model.dto.community.CommunityBoard;
import com.baseball.bunt.model.dto.community.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService{

    private final CommunityDao dao;

    @Override
    public List<CommunityBoard> readBoardList(SearchCondition searchCondition) {   // 게시판 리스트
        return dao.boardList(searchCondition);
    }

    @Override
    public CommunityBoard readBoardDetail(final int communityBoardId) {   // 게시글 상세 보기
        return dao.getDetail(communityBoardId);
    }

    @Override
    public int createBoard(final CommunityBoard board) {  // 게시글 생성
        return dao.insertBoard(board);
    }

    @Override
    @Transactional
    public int removeBoardByBoardId(final int communityBoardId) { // 게시글 삭제
        return dao.deleteBoard(communityBoardId);
    }

    @Override
    @Transactional
    public int modifyBoardByBoardId(final CommunityBoard board) { // 게시글 수정
        return dao.updateBoard(board);
    }

}
