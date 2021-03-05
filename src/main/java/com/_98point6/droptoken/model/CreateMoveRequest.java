package com._98point6.droptoken.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.base.Preconditions;

/**
 *
 */
public class CreateMoveRequest {
    private Integer column;

    private CreateMoveRequest() {}

    public CreateMoveRequest(Builder builder) {
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

        public Builder fromPrototype(CreateMoveRequest prototype) {
            column = prototype.column;
            return this;
        }

        public CreateMoveRequest build() {
            CreateMoveRequest req = new CreateMoveRequest();
            req.column = this.column;
            return req;
        }
    }
}
