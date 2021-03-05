package com._98point6.droptoken.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com._98point6.droptoken.bo.Game;
import com._98point6.droptoken.bo.Move;
import com._98point6.droptoken.bo.Player;
import com._98point6.droptoken.service.exception.IllegalMoveException;
import com._98point6.droptoken.service.exception.InvalidInputException;

public class FakeGameDB {

	private static Map<Game, List<Move>> games = new HashMap<Game, List<Move>>();
	private static Map<String, int[][]> board = new HashMap<String, int[][]>();

	/**
	 *  creates a generic board with the preferred size
	 * @param gameId
	 * @param column
	 * @param row
	 */
	private void createBoard(String gameId, int column, int row) {
		int[][] columns = new int[column][row];
		this.board.put(gameId, columns);
	}

	/**
	 * add Tokens to the board in each column
	 * @param gameId
	 * @param index
	 * @param token
	 * @param column
	 * @param row
	 */
	private void addToken(String gameId, int index, int token, int column, int row) {
		int[][] arr = board.get(gameId);
		if (index > column) {
			throw new InvalidInputException(
					"[" + index + "] should be less than board size " + column + " x " + row + ".");
		}
		boolean isColumnFull = true;
		for (int i = 0; i < row; i++) {
			if (arr[i][index] == 0) {
				arr[i][index] = token;
				isColumnFull = false;
				break;
			}
		}

		if (isColumnFull) {
			throw new IllegalMoveException(" Column:" + index + " is already full. Please try a different column !! ");
		}
		this.board.put(gameId, arr);
	}

	/**
	 * checks winner horizontally, vertically & diagonally
	 * @param arr
	 * @param column
	 * @param row
	 * @return
	 */
	private int checkWinner(int arr[][], int column, int row) {

		int[] tokens = new int[10];
		for (int i = 0; i < column; i++) {
			// diagonal1
			tokens[0] = arr[i][i] + tokens[0];
			// diagonal2
			tokens[1] = arr[column - 1 - i][i] + tokens[1];
			for (int j = 0; j < row; j++) {
				// vertical
				tokens[i + 2] = arr[j][i] + tokens[i + 2];
				// horizontal
				tokens[i + 6] = arr[i][j] + tokens[i + 6];
			}
		}
		for (int i = 0; i < column + row; i++) {
			if (tokens[i] == 4) {
				return 1;
			} else if (tokens[i] == 20) {
				return 5;
			}
		}
		return 0;
	}

	public String createGame(Game game) {
		createBoard(game.getId(), game.getColumns(), game.getRows());
		games.put(game, new ArrayList<Move>());
		return game.getId();
	}

	public List<Game> getAllGames() {
		List<Game> gameslist = new ArrayList(games.keySet());
		return gameslist;
	}

	public List<Move> getMoves(String gameId) {
		List<Move> moves = games.get(new Game(gameId));
		return moves;
	}

	/**
	 * Post a Move
	 * 
	 * @param map
	 * @return
	 */
	public String addMove(HashMap<String, String> map) {
		Game game = getGameObject(map.get("gameId"));
		int column = Integer.parseInt(map.get("column"));
		validateGame(game);
		List<Move> moves = games.get(game);
		if (moves.size() > 16) {
			throw new IllegalMoveException(" Game was dull & your Board is full, Try a new Game!! ");
		}

		Player _player = new Player(map.get("player"));

		if (game.getParticipant().getPlayers().get(_player) == null) {
			throw new InvalidInputException(" Thy shall not pass !! You are not a valid Player ");
		}

		int currentFlag = game.getParticipant().getPlayers().get(_player);
		if (currentFlag == 0)
			throw new InvalidInputException(" Nope, I dont recognize you mate !! Please provide a valid player");

		if (moves.size() > 0) {
			Move prevMove = moves.get(moves.size() - 1);
			int prevFlag = prevMove.getColorFlag();
			// Stop players if its not their turn
			if (prevFlag == currentFlag) {
				throw new IllegalMoveException(" Not so fast dear, its your opponents turn...");
			}
		}
		Move move = new Move();
		move.setStep(moves.size());
		move.setColumn(column);
		move.setPlayer(new Player(map.get("player")));
		move.setTypeCode(DropTokenConstant.MOVE);
		move.setColorFlag(currentFlag);
		moves.add(move);
		// int token = game.getParticipant().getPlayers().get(_player);

		addToken(map.get("gameId"), column, currentFlag, game.getColumns(), game.getRows());

		if (moves.size() > 6) {
			int winner = checkWinner(board.get(game.getId()), game.getColumns(), game.getRows());
			for (Player wplayer : game.getParticipant().getPlayers().keySet()) {
				if (winner == game.getParticipant().getPlayers().get(wplayer)) {
					game.setWinner(wplayer.getNickName());
					game.setState(DropTokenConstant.DONE);
				}
			}
		}
		games.put(game, moves);
		return map.get("gameId") + "/moves/" + (moves.size() - 1);
	}

	public Game getGameObject(String gameId) {
		for (Game game : games.keySet()) {
			if (game.getId().equals(gameId)) {
				return game;
			}
		}
		return null;
	}

	public void validateGame(Game game) {
		if (game == null) {
			throw new InvalidInputException("Not a valid GameId ");
		}
		if (game.getState().contains(DropTokenConstant.DONE)) {
			throw new IllegalMoveException("Too late dear, Game over!! ");
		}
	}

	/**
	 * Quitting a game
	 * @param map
	 * @return
	 */
	public boolean quitGame(HashMap<String, String> map) {
		Game game = getGameObject(map.get("gameId"));
		validateGame(game);
		Player player = new Player(map.get("player"));
		boolean isValidPlayer = game.getParticipant().getPlayers().containsKey(new Player(map.get("player")));

		if (!isValidPlayer) {
			throw new InvalidInputException("Thy shall not pass !! You are not a valid Player ");
		}

		List<Move> moves = games.get(game);
		Move move = new Move();
		move.setStep(moves.size());
		move.setTypeCode(DropTokenConstant.QUIT);
		move.setPlayer(player);
		moves.add(move);
		game.setState(DropTokenConstant.DONE);
		Timestamp endTS = new Timestamp(System.currentTimeMillis());
		game.setEndTS(endTS);
		// Declaring the opponent as winner since the player quit the game
		for (Player _p : game.getParticipant().getPlayers().keySet()) {
			if (!_p.getNickName().equals(map.get("player"))) {
				game.setWinner(_p.getNickName());
			}
		}
		games.put(game, moves);
		return true;
	}

	/**
	 * Get a specific move
	 * @param imap
	 * @return
	 */
	public Map<String, String> getMove(HashMap<String, String> imap) {
		Map<String, String> map = new HashMap<String, String>();

		// Invalid game if there is no gameId provided
		if (imap.get("gameId") == null) {
			throw new InvalidInputException("Not a valid GameId ");
		}
		List<Move> moves = games.get(new Game(imap.get("gameId")));
		// Invalid game if there is no Moves found
		if (moves == null) {
			throw new InvalidInputException("Not a valid GameId ");
		}
		Move move = null;
		for (Move m : moves) {
			if (m.getId() == Integer.parseInt(imap.get("moveId"))) {
				move = m;
				break;
			}
		}
		if (move == null) {
			throw new IllegalMoveException(
					"Aha!! [" + imap.get("moveId") + "] not a valid move for game: " + imap.get("gameId"));
		}
		map.put("type", move.getTypeCode());
		map.put("player", move.getPlayer().getNickName());
		map.put("column", "" + move.getColumn());
		return map;
	}
}