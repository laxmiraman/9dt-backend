package com._98point6.droptoken.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com._98point6.droptoken.bo.Move;
import com._98point6.droptoken.dao.MoveDAOImpl;
import com._98point6.droptoken.model.GetMoveResponse;
import com._98point6.droptoken.model.GetMovesRequest;
import com._98point6.droptoken.model.GetMovesResponse;
import com._98point6.droptoken.service.exception.InvalidInputException;

public class MoveService {

	public GetMovesResponse getMoves(GetMovesRequest request) {
		MoveDAOImpl moveDAOImpl = new MoveDAOImpl();
		List<Move> movesList = moveDAOImpl.getMoves(request.getGameId());
		List<GetMoveResponse> list = new ArrayList<GetMoveResponse>();
		GetMoveResponse moveResp = null;
		GetMovesResponse reply = null;
		
		if (movesList == null) {
			throw new InvalidInputException(" Not a valid game !! ");
		}
		
		int recordSize = movesList.size();
		int start = request.getStart();
		int until = request.getUntil();

		if (start > recordSize || until > recordSize) {
			throw new InvalidInputException("Not a valid range of moves found for the game: " + request.getGameId());
		}

		if (start == 0 && until == 0) {
			for (int i = 0; i < recordSize; i++) {
				Move move = movesList.get(i);
				moveResp = new GetMoveResponse.Builder().column(move.getColumn()).type(move.getTypeCode())
						.player(move.getPlayer().getNickName()).build();
				list.add(moveResp);
			}
		} else {
			for (int i = start; i <= until; i++) {
				Move move = movesList.get(i);
				moveResp = new GetMoveResponse.Builder().column(move.getColumn()).type(move.getTypeCode())
						.player(move.getPlayer().getNickName()).build();
				list.add(moveResp);
			}
		}

		reply = new GetMovesResponse.Builder().moves(list).build();
		return reply;
	}

	public String postMove(HashMap map) {
		MoveDAOImpl moveDAOImpl = new MoveDAOImpl();
		return moveDAOImpl.save(map);
	}

	public void quitGame(HashMap map) {
		MoveDAOImpl moveDAOImpl = new MoveDAOImpl();
		moveDAOImpl.update(map);
	}

	public GetMoveResponse getMove(HashMap map) {
		MoveDAOImpl moveDAOImpl = new MoveDAOImpl();
		HashMap<String, String> rmap = (HashMap<String, String>) moveDAOImpl.getMove(map);

		GetMoveResponse response = new GetMoveResponse.Builder().type(rmap.get("type")).player(rmap.get("player"))
				.column(Integer.parseInt(rmap.get("column"))).build();
		return response;
	}

}
