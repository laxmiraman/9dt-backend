package com._98point6.droptoken.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.base.Preconditions;

/**
 *
 */
public class PostMoveRequest {
    private Integer column;

    private PostMoveRequest() {}

    public PostMoveRequest(Builder builder) {
        this.column = Preconditions.checkNotNull(builder.column);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("column", column)
                .toString();
    }

    public Integer getColumn() {
        return column;
    }

    public static class Builder {
        private Integer column;

        public Builder column(Integer column) {
            this.column = column;
            return this;
        }

        public Builder fromPrototype(PostMoveRequest prototype) {
            column = prototype.column;
            return this;
        }

        public PostMoveRequest build() {
            PostMoveRequest req = new PostMoveRequest();
            req.column = this.column;
            return req;
        }
    }
}
