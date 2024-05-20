package com.baseball.bunt.controller;

import com.baseball.bunt.model.dto.community.CommunityBoard;
import com.baseball.bunt.model.dto.community.CommunityBoardComment;
import com.baseball.bunt.model.service.CommunityCommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Community Comment Controller", description = "커뮤니티 게시글 댓글을 관리하는 컨트롤러")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board/comment")
public class CommunityCommentController {

    private final CommunityCommentService service;

    // 댓글 리스트
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getList(@PathVariable int boardId){
        List<CommunityBoardComment> commentList = service.readCommentList(boardId);
        if (commentList == null || commentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    // 댓글 등록
    @PostMapping()
    public ResponseEntity<?> createComment(@RequestBody CommunityBoardComment comment){

        int result = service.createComment(comment);
        if (result == 1) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> modifyBoard(@PathVariable int commentId, @RequestBody CommunityBoardComment comment){
        comment.setCommunityBoardCommentId(commentId);
        int result = service.modifyComment(comment);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId){
        int result = service.removeComment(commentId);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
