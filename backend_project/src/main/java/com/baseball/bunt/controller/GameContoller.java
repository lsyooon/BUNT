package com.baseball.bunt.controller;

import com.baseball.bunt.model.dto.game.BaseballGame;
import com.baseball.bunt.model.service.BaseballGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Game Controller", description = "미니게임을 관리하는 컨트롤러")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/game")
public class GameContoller {
    private final BaseballGameService service;
    
    @Operation(summary = "랭킹 목록")
    @GetMapping()
    public ResponseEntity<?> getRanking() {
        List<BaseballGame> ranking = service.getRanking();
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @Operation(summary = "기록 등록")
    @PostMapping()
    public ResponseEntity<?> createGame(@RequestBody BaseballGame game) {
        int result = service.addScore(game);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
