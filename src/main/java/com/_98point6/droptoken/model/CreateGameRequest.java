package com._98point6.droptoken.model;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com._98point6.droptoken.bo.Player;
import com.google.common.base.Preconditions;

public class CreateGameRequest {
	private List<Player> players;
	private Integer columns;
	private Integer rows;

	public CreateGameRequest() {
	}

	public CreateGameRequest(Builder builder) {
		this.players = Preconditions.checkNotNull(builder.players);
		this.columns = Preconditions.checkNotNull(builder.columns);
		this.rows = Preconditions.checkNotNull(builder.rows);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("players", players).append("columns", columns).append("rows", rows)
				.toString();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Integer getColumns() {
		return columns;
	}

	public Integer getRows() {
		return rows;
	}

	public static class Builder {
		private List<Player> players;
		private Integer columns;
		private Integer rows;

		public Builder players(List<Player> _players) {
			this.players = _players;
			return this;
		}

		public Builder columns(Integer _columns) {
			this.columns = _columns;
			return this;
		}

		public Builder rows(Integer _rows) {
			this.rows = _rows;
			return this;
		}

		public Builder fromPrototype(CreateGameRequest prototype) {
			players = prototype.players;
			columns = prototype.columns;
			rows = prototype.rows;
			return this;
		}

		public CreateGameRequest build() {
			CreateGameRequest req = new CreateGameRequest();
			req.players = this.players;
			req.columns = this.columns;
			req.rows = this.rows;
			return req;
		}
	}
}