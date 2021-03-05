package com._98point6.droptoken.model;

import java.util.List;

import com.google.common.base.Preconditions;

/**
 *
 */
public class GetGamesResponse {
    private List<String> games;

    public GetGamesResponse() {}

    public GetGamesResponse(Builder builder) {
        this.games = Preconditions.checkNotNull(builder.games);
    }

    public List<String> getGames() {
        return games;
    }
    
    public void setGames(List<String> _games) {
    	this.games = _games;
    }
    
    @Override
    public String toString() {
    	return games.toString();
    	
    }

    public static class Builder {
        private List<String> games;

        public Builder games(List<String> games) {
            this.games = games;
            return this;
        }

        public Builder fromPrototype(GetGamesResponse prototype) {
            games = prototype.games;
            return this;
        }

        public GetGamesResponse build() {
        	GetGamesResponse resp = new GetGamesResponse();
        	resp.games = this.games;
        	return resp;
        }
    }
}
