package com._98point6.droptoken.dao;

import java.util.List;

import com._98point6.droptoken.bo.Game;
import com._98point6.droptoken.service.RandomString;

public class GameDAOImpl implements IDAO{
	
	FakeGameDB db = new FakeGameDB();
	
	@Override
	public List<Game> loadAll() {
		return db.getAllGames();
	}

	@Override
	public String save(Object obj) {
		Game game = (Game) obj;
		return db.createGame(game);		
	}

	@Override
	public void update(Object domain) {
		
		
	}

	@Override
	public void delete(Object domain) {
		// TODO Auto-generated method stub
		
	}	
	
}