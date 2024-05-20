package com.baseball.bunt.model.service;

import java.util.List;

import com.baseball.bunt.model.dto.team.Player;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "선수 서비스")

public interface PlayerService {
	int createPlayer(Player player);

	Player findPlayer(Player player);

	Player findPlayerById(int playerId);

	List<Player> findPlayersByName(String name);

	List<Player> findPlayersByPosition(String position);

	List<Player> findPlayersByTeamId(int teamId);

	int modifyPlayer(Player player);

	int removePlayer(int playerId);
}
