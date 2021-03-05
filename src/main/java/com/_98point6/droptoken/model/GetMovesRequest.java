package com._98point6.droptoken.model;

import com.google.common.base.Preconditions;

public class GetMovesRequest {
    private String gameId;
    private int start;
    private int until;

    public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getUntil() {
		return until;
	}

	public void setUntil(int until) {
		this.until = until;
	}

	private GetMovesRequest() {}

    public GetMovesRequest(Builder builder) {
        this.gameId = Preconditions.checkNotNull(builder.gameId);
        this.start = Preconditions.checkNotNull(builder.start);
        this.until = Preconditions.checkNotNull(builder.until);
    }

    public static class Builder {
        private String gameId;
        private int start;
        private int until;

        public String getGameId() {
    		return gameId;
    	}

    	public Builder setGameId(String gameId) {
    		this.gameId = gameId;
    		return this;
    	}

    	public int getStart() {
    		return start;
    	}

    	public Builder setStart(int start) {
    		this.start = start;
    		return this;
    	}

    	public int getUntil() {
    		return until;
    	}

    	public Builder setUntil(int until) {
    		this.until = until;
    		return this;
    	}

        public Builder fromPrototype(GetMovesRequest prototype) {
            gameId = prototype.gameId;
            start = prototype.start;
            until = prototype.until;
            return this;
        }

        public GetMovesRequest build() {
        	GetMovesRequest request =  new GetMovesRequest();
        	request.gameId = this.gameId;
        	request.start = this.start;
        	request.until = this.until;
        	return request;
        }
    }
}