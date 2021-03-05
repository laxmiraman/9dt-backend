package com._98point6.droptoken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com._98point6.droptoken.bo.Game;
import com._98point6.droptoken.bo.Player;
import com._98point6.droptoken.dao.DropTokenConstant;
import com._98point6.droptoken.model.CreateGameRequest;
import com._98point6.droptoken.model.CreateGameResponse;
import com._98point6.droptoken.model.GameStatusResponse;
import com._98point6.droptoken.model.GetGamesResponse;
import com._98point6.droptoken.model.GetMovesRequest;
import com._98point6.droptoken.model.PostMoveRequest;
import com._98point6.droptoken.model.PostMoveResponse;
import com._98point6.droptoken.service.GameService;
import com._98point6.droptoken.service.MoveService;
import com._98point6.droptoken.service.exception.InvalidInputException;

@Path("/drop_token")
@Produces(MediaType.APPLICATION_JSON)
public class DropTokenResource {
	private static final Logger logger = LoggerFactory.getLogger(DropTokenResource.class);

	public DropTokenResource() {
	}

	@GET
	public Response getAllActiveGames() {
		GameService gameService = new GameService();
		List<String> list = new ArrayList<String>();
		for (Game game : gameService.getAllActiveGames()) {
			list.add(game.getId());
		}
		GetGamesResponse resp = new GetGamesResponse.Builder().games(list).build();
		return Response.ok(resp).build();
	}

	@POST
	public Response createGame(CreateGameRequest request) {
		GameService gameService = new GameService();
		CreateGameResponse resp = gameService.createGame(request);
		return Response.ok(resp).build();
	}

	@Path("/{id}")
	@GET
	public Response getGameStatus(@PathParam("id") String gameId) {
		// logger.info(">>>>>>>>>>>>>>>>>>>getGameStatus", gameId);
		GameService gameService = new GameService();
		Game game = gameService.getGameStatus(gameId);

		if (game == null) {
			throw new InvalidInputException(" Not a valid game !! ");
		}

		List<String> playersList = new ArrayList<String>();
		for (Player p : game.getParticipant().getPlayers().keySet()) {
			playersList.add(p.getNickName());
		}

		GameStatusResponse resp;
		resp = new GameStatusResponse.Builder().players(playersList).state(game.getState()).winner(game.getWinner())
				.build();

		System.out.println("Response :" + resp);
		return Response.ok(resp).build();
	}

	@Path("/{id}/moves")
	@GET
	public Response getMoves(@PathParam("id") String gameId, @DefaultValue("0") @QueryParam("start") Integer start,
			@DefaultValue("0") @QueryParam("until") Integer until) {
		// logger.info("gameId={}, start={}, until={}", gameId, start);

		GetMovesRequest moveReq = new GetMovesRequest.Builder().setGameId(gameId).setStart(start).setUntil(until)
				.build();

		MoveService service = new MoveService();
		return Response.ok(service.getMoves(moveReq)).build();
	}

	@Path("/{id}/{playerId}")
	@POST
	public Response postMove(@PathParam("id") String gameId, @PathParam("playerId") String playerId,
			PostMoveRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", playerId);
		map.put("column", "" + request.getColumn());
		MoveService moveService = new MoveService();
		String moveLink = moveService.postMove(map);
		PostMoveResponse response = new PostMoveResponse.Builder().move(moveLink).build();

		return Response.ok(response).build();
	}

	@Path("/{id}/{playerId}")
	@DELETE
	public Response playerQuit(@PathParam("id") String gameId, @PathParam("playerId") String playerId) {
		// logger.info("gameId={}, playerId={}", gameId, playerId);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("player", playerId);
		MoveService moveService = new MoveService();
		moveService.quitGame(map);
		return Response.status(202).build();
	}

	@Path("/{id}/moves/{moveId}")
	@GET
	public Response getMove(@PathParam("id") String gameId, @PathParam("moveId") Integer moveId) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("gameId", gameId);
		map.put("moveId", "" + moveId);
		MoveService service = new MoveService();
		return Response.ok(service.getMove(map)).build();
	}

}
