package com._98point6.droptoken.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;

public class GameStatusResponse {
    private List<String> players;
    private String winner;
    private String state;

    public GameStatusResponse() {}

    public GameStatusResponse(Builder builder) {
        this.players = Preconditions.checkNotNull(builder.players);
        this.winner = Preconditions.checkNotNull(builder.winner);
        this.state = Preconditions.checkNotNull(builder.state);
    }

    public List<String> getPlayers() {
        return players;
    }

    public String getWinner() {
        return winner;
    }

    public String getState() {
        return state;
    }
    
    public String toString() {
    	return "players: "+getPlayers()+", winner: "+getWinner()+", state: "+getState();
    }

    public static class Builder {
        private List<String> players;
        private String winner;
        private String state;

        public Builder players(List<String> players) {
            this.players = players;
            return this;
        }

        public Builder moves(Integer moves) {
            return this;
        }

        public Builder winner(String winner) {
            this.winner = winner;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder fromPrototype(GameStatusResponse prototype) {
            players = prototype.players;
            winner = prototype.winner;
            state = prototype.state;
            return this;
        }

        public GameStatusResponse build() {
        	GameStatusResponse resp = new GameStatusResponse();
        	resp.players = this.players;
        	resp.winner = this.winner;
        	resp.state = this.state;
            return resp;
        }
    }
}