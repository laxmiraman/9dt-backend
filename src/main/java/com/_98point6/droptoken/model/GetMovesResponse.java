package com._98point6.droptoken.model;

import java.util.List;

import com.google.common.base.Preconditions;

/**
 *
 */
public class GetMovesResponse {
    private List<GetMoveResponse> moves;

    private GetMovesResponse() {}

    public GetMovesResponse(Builder builder) {
        this.moves = Preconditions.checkNotNull(builder.moves);
    }

    public List<GetMoveResponse> getMoves() {
        return moves;
    }

    public static class Builder {
        private List<GetMoveResponse> moves;

        public Builder moves(List<GetMoveResponse> moves) {
            this.moves = moves;
            return this;
        }

        public Builder fromPrototype(GetMovesResponse prototype) {
            moves = prototype.moves;
            return this;
        }

        public GetMovesResponse build() {
        	GetMovesResponse resp =  new GetMovesResponse();
        	resp.moves = this.moves;
        	return resp;
        }
    }
}
