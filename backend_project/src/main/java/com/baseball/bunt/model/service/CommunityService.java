package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dto.community.Community;
import com.baseball.bunt.model.dto.community.CommunityBoard;
import com.baseball.bunt.model.dto.community.Criteria;

import java.util.List;

public interface CommunityService {

    // 게시글 목록
    List<CommunityBoard> readBoardList(Criteria cri);

    // 게시글 상세 보기
    CommunityBoard readBoardDetail(int communityBoardId);

    // 게시글 생성
    int createBoard(CommunityBoard board);

    // 게시글 삭제
    int removeBoardByBoardId(int communityBoardId);

    // 게시글 수정
    int modifyBoardByBoardId(CommunityBoard board);

    // 총 페이지 수
    public int getTotal(Criteria cri);

}
