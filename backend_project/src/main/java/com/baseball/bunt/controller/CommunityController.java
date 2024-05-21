package com.baseball.bunt.controller;

import com.baseball.bunt.model.dto.community.CommunityBoard;
import com.baseball.bunt.model.dto.community.LikeList;
import com.baseball.bunt.model.dto.community.SearchCondition;
import com.baseball.bunt.model.service.CommunityService;
import com.baseball.bunt.model.service.LikeListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Community Controller", description = "커뮤니티를 관리하는 컨트롤러")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class CommunityController {

    private final CommunityService communityService;
    private final LikeListService likeService;


    @Operation(summary = "게시글 목록")
    @GetMapping("/{teamId}")
    public ResponseEntity<?> readBoardList(@ModelAttribute SearchCondition searchCondition) {
        List<CommunityBoard> boardList = communityService.readBoardList(searchCondition);
        if (boardList == null || boardList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @Operation(summary = "게시글 상세 보기")
    @GetMapping("/{teamId}/{boardId}")
    public ResponseEntity<?> readBoard(@PathVariable int boardId) {
        CommunityBoard board = communityService.readBoardDetail(boardId);
        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @Operation(summary = "게시글 생성")
    @PostMapping()
    public ResponseEntity<?> createBoard(@RequestBody CommunityBoard board) {
        int result = communityService.createBoard(board);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{teamId}/{boardId}")
    public ResponseEntity<Void> modifyBoard(@PathVariable int boardId, @RequestBody CommunityBoard board) {
        board.setCommunityBoardId(boardId);
        int result = communityService.modifyBoardByBoardId(board);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{teamId}/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId) {
        int result = communityService.removeBoardByBoardId(boardId);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    // LikeListController
    @Operation(summary = "좋아요 리스트")
    @GetMapping("/like/{userId}")
    public ResponseEntity<?> likeList(@PathVariable String userId) {
        List<LikeList> list = likeService.getLikeList(userId);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "좋아요 여부 확인")
    @GetMapping("/like/{userId}/{boardId}")
    public ResponseEntity<?> isLiked(@PathVariable String userId, @PathVariable int boardId) {
        int result = likeService.find_like(userId, boardId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "해당 게시글 좋아요 개수")
    @GetMapping("/like/cnt/{boardId}")
    public ResponseEntity<?> likeCnt(@PathVariable int boardId) {
        int result = likeService.likeCnt(boardId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "좋아요 추가, 삭제")
    @PostMapping("/like/{userId}/{boardId}")
    public ResponseEntity<Integer> like(@PathVariable String userId, @PathVariable int boardId) {
        int like = likeService.find_like(userId, boardId);
        if (like >= 1) {
            likeService.removeLike(boardId, userId);
            like = 0;
        } else {
            likeService.addLike(boardId, userId);
            like = 1;
        }
        return ResponseEntity.ok(like);
    }


}

