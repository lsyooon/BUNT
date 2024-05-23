package com.baseball.bunt.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baseball.bunt.model.dao.CommunityDao;
import com.baseball.bunt.model.dto.community.CommunityBoard;
import com.baseball.bunt.model.dto.community.SearchCondition;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService{

    private final CommunityDao dao;

    @Override
    public List<CommunityBoard> readBoardList(SearchCondition searchCondition) {
        return dao.boardList(searchCondition);
    }

    @Override
    public CommunityBoard readBoardDetail(final int communityBoardId) {   // 게시글 상세 보기
        return dao.getDetail(communityBoardId);
    }

    @Override
    public int createBoard(final CommunityBoard board) {
        System.out.println("Creating board with userId: " + board.getUserId()); // 디버깅 로그
        try {
            return dao.insertBoard(board);
        } catch (Exception e) {
            e.printStackTrace(); // 오류 스택 트레이스 출력
            throw e; // 예외 다시 던지기
        }
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
