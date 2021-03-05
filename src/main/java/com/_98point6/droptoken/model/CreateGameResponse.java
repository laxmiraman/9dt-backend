package com._98point6.droptoken.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.google.common.base.Preconditions;

public class CreateGameResponse {
	private String gameId;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public CreateGameResponse() {
	}

	public CreateGameResponse(Builder builder) {
		this.gameId = Preconditions.checkNotNull(builder.gameId);
	}

	@Override
	public String toString() {
		return gameId;
	}

	public static class Builder {
		private String gameId;

		public Builder setGameId(String _gameId) {
			this.gameId = _gameId;
			return this;
		}

		public Builder() {
		}

		public CreateGameResponse build() {
			CreateGameResponse resp = new CreateGameResponse();
			resp.gameId = this.gameId;
			return resp;
		}
	}
}
