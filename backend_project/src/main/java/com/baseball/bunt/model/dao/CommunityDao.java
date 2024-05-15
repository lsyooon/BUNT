package com.baseball.bunt.model.dao;

import com.baseball.bunt.model.dto.community.CommunityBoard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityDao {

    // 게시글 전체 리스트
    List<CommunityBoard> boardList();

    // 게시글 상세 보기
    CommunityBoard getDetail(int communityBoardId);

    // 게시글 생성
    int insertBoard(CommunityBoard board);

    // 게시글 삭제
    int deleteBoard(int communityBoardId);

    // 게시글 수정
    int updateBoard(CommunityBoard board);

}
