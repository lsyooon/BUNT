package com.baseball.bunt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.bunt.model.dto.team.Player;
import com.baseball.bunt.model.service.PlayerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Player Controller", description = "선수 관리하는 컨트롤러")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
	RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/player")
public class PlayerController {

	private final PlayerService playerService;

	@GetMapping("/find/{teamId}")
	public ResponseEntity<?> findPlayersByTeamId(@PathVariable("teamId") int teamId) {
		try {
			List<Player> teamPlayers = playerService.findPlayersByTeamId(teamId);
			if (teamPlayers != null && !teamPlayers.isEmpty()) {
				return new ResponseEntity<>(teamPlayers, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>("[ERROR] " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
