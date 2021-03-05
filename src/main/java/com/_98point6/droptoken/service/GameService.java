package com._98point6.droptoken.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com._98point6.droptoken.bo.Game;
import com._98point6.droptoken.bo.Participant;
import com._98point6.droptoken.bo.Player;
import com._98point6.droptoken.dao.GameDAOImpl;
import com._98point6.droptoken.model.CreateGameRequest;
import com._98point6.droptoken.model.CreateGameResponse;

public class GameService {

	public CreateGameResponse createGame(CreateGameRequest req) {
	    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		GameDAOImpl gameDAOImpl = new GameDAOImpl();		
		Game game = new Game();
		game.setColumns(req.getColumns());
		game.setRows(req.getRows());
		game.setState("IN_PROGRESS");
		game.setId(RandomString.generateString());
		game.setStartTS(timestamp);
		Participant participant = new Participant();
		Map<Player, Integer> pmap = new HashMap<Player, Integer>(2);
		pmap.put(req.getPlayers().get(0), 1);
		pmap.put(req.getPlayers().get(1), 5);
		participant.setPlayers(pmap);
		game.setParticipant(participant);
		String gameId = gameDAOImpl.save(game);
		CreateGameResponse response = new CreateGameResponse
				.Builder()
				.setGameId(gameId)
				.build();
		
		return response;
	}

	public List<Game> getAllActiveGames() {
		GameDAOImpl gameDAOImpl = new GameDAOImpl();
		List<Game> result = new ArrayList<Game>();
		for (Game g : gameDAOImpl.loadAll()) {
			if (g.getState().equals("IN_PROGRESS")) {
				result.add(g);
			}
		}
		return result;
	}

	public Game getGameStatus(String gameId) {
		Game tgtGame = null;
		GameDAOImpl gameDAOImpl = new GameDAOImpl();
		for (Game g : gameDAOImpl.loadAll()) {
			if (g.getId().equals(gameId)) {
				return g;				
			}
		}
		return null;				
	}
	
}