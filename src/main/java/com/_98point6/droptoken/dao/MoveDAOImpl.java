package com._98point6.droptoken.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com._98point6.droptoken.bo.Move;

public class MoveDAOImpl implements IDAO {

	FakeGameDB db = new FakeGameDB();

	@Override
	public List<Move> loadAll() {
		return null;
	}

	public Map<String, String> getMove(HashMap map) {
		return db.getMove(map);
	}

	public List<Move> getMoves(String gameId) {
		return db.getMoves(gameId);
	}

	public String save(HashMap map) {
		FakeGameDB db = new FakeGameDB();
		return db.addMove(map);
	}

	@Override
	public void update(Object domain) {
		HashMap<String, String> map = (HashMap) domain;
		FakeGameDB db = new FakeGameDB();
		db.quitGame(map);
	}

	@Override
	public void delete(Object domain) {
		// TODO Auto-generated method stub

	}

	@Override
	public String save(Object domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
