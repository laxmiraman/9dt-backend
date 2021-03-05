package com._98point6.droptoken.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com._98point6.droptoken.DropTokenResource;
import com._98point6.droptoken.bo.Game;
import com._98point6.droptoken.bo.Player;
import com._98point6.droptoken.model.CreateGameRequest;
import com._98point6.droptoken.model.CreateGameResponse;

public class TestCol {
	public static void main(String args[]) {
		List<Player> _players = new ArrayList<Player>();
		Player p1 = new Player("Shifu");
		Player p2 = new Player("Oogway");
		_players.add(p1);
		_players.add(p2);
		DropTokenResource dT = new DropTokenResource();
		GameService service = new GameService();
		CreateGameRequest req = new CreateGameRequest.Builder().columns(4).rows(4).players(_players).build();
		CreateGameResponse resp = service.createGame(req);
		String gameId = resp.getGameId();
		System.out.println("gameId: " + gameId);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", "Shifu");
		map.put("column", "" + 0);
		MoveService moveService = new MoveService();
		String moveLink = moveService.postMove(map);

		map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", "Oogway");
		map.put("column", "" +1);
		moveService = new MoveService();
		moveLink = moveService.postMove(map);

		map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", "Shifu");
		map.put("column", "" + 0);
		moveService = new MoveService();
		moveLink = moveService.postMove(map);

		map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", "Oogway");
		map.put("column", "" +1);
		moveService = new MoveService();
		moveLink = moveService.postMove(map);
		

		map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", "Shifu");
		map.put("column", "" +0);
		moveService = new MoveService();
		moveLink = moveService.postMove(map);
		
		map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", "Oogway");
		map.put("column", "" +1);
		moveService = new MoveService();
		moveLink = moveService.postMove(map);

		map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", "Shifu");
		map.put("column", "" +0);
		moveService = new MoveService();
		moveLink = moveService.postMove(map);
		

		dT.getMoves(gameId, 0, 0);
		dT.getGameStatus(gameId);		
		
	}

}
