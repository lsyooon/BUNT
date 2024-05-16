package com.baseball.bunt.controller;

import com.baseball.bunt.model.dto.community.CommunityBoard;
import com.baseball.bunt.model.dto.community.Criteria;
import com.baseball.bunt.model.dto.community.Page;
import com.baseball.bunt.model.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Community Controller", description = "커뮤니티를 관리하는 컨트롤러")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class CommunityController {

    private final CommunityService communityService;

    @Operation(summary = "게시글 목록")
    @GetMapping("/")
    public ResponseEntity<?> readBoardList(Criteria cri) {
        List<CommunityBoard> boardList = communityService.readBoardList(cri);
        int total = communityService.getTotal(cri);
        Page pageMaker = new Page(cri, total);  // 123은 총 게시글 수를 예로 든 것

        if (boardList == null || boardList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("pageMaker", pageMaker);
        response.put("boardList", boardList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "게시글 상세 보기")
    @GetMapping("/{boardId}")
    public ResponseEntity<?> readBoard(@PathVariable int boardId) {
        CommunityBoard board = communityService.readBoardDetail(boardId);
        if (board == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @Operation(summary = "게시글 생성")
    @PostMapping("/new")
    public ResponseEntity<?> createBoard(@RequestBody CommunityBoard board) {
        int result = communityService.createBoard(board);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/modify/{boardId}")
    public ResponseEntity<Void> modifyBoard(@PathVariable int boardId, @RequestBody CommunityBoard board) {
        board.setCommunityBoardId(boardId);
        int result = communityService.modifyBoardByBoardId(board);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId) {
        int result = communityService.removeBoardByBoardId(boardId);
        if (result == -1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
