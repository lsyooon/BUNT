package com.baseball.bunt.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baseball.bunt.model.dto.team.Player;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "선수 DAO")
@Repository
public interface PlayerDao {
	int insertPlayer(Player player);

	Player selectPlayer(Player player);

	Player selectPlayerById(Integer playerId);

	List<Player> selectPlayersByName(String name);

	List<Player> selectPlayersByPosition(String position);

	List<Player> selectPlayersByTeamId(int teamId);

	int updatePlayer(Player player);

	int deletePlayer(int playerId);
}
