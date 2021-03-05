package com._98point6.droptoken.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.base.Preconditions;

public class PostMoveResponse {
    private String move;

    private PostMoveResponse() {}

    public PostMoveResponse(Builder builder) {
        this.move = Preconditions.checkNotNull(builder.move);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("move", move)
                .toString();
    }

    public String getMoveLink() {
        return move;
    }

    public static class Builder {
        private String move;

        public Builder move(String moveLink) {
            this.move = moveLink;
            return this;
        }

        public Builder fromPrototype(PostMoveResponse prototype) {
            move = prototype.move;
            return this;
        }

        public PostMoveResponse build() {
        	PostMoveResponse resp = new PostMoveResponse();
        	resp.move = this.move;
        	return resp;
        }
    }
}