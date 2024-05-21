package com.baseball.bunt.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baseball.bunt.model.dao.PlayerDao;
import com.baseball.bunt.model.dto.team.Player;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "선수 서비스 구현체")
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService{

	private final PlayerDao playerDao;

	@Override
	public int createPlayer(final Player player) {
		return playerDao.insertPlayer(player);
	}

	@Override
	public Player findPlayer(final Player player) {
		return playerDao.selectPlayer(player);
	}

	@Override
	public Player findPlayerById(final int playerId) {
		return playerDao.selectPlayerById(playerId);
	}

	@Override
	public List<Player> findPlayersByName(final String name) {
		return playerDao.selectPlayersByName(name);
	}

	@Override
	public List<Player> findPlayersByPosition(final String position) {
		return playerDao.selectPlayersByPosition(position);
	}

	@Override
	public List<Player> findPlayersByTeamId(final int teamId) {
		return playerDao.selectPlayersByTeamId(teamId);
	}

	@Override
	public int modifyPlayer(Player player) {
		return playerDao.updatePlayer(player);
	}

	@Override
	public int removePlayer(final int playerId) {
		return playerDao.deletePlayer(playerId);
	}
}
