package com._98point6.droptoken.model;

import com.google.common.base.Preconditions;

public class GetMoveResponse {
	private String type;
    private String player;
    private Integer column;

    public GetMoveResponse() {}

    public GetMoveResponse(Builder builder) {
        this.type = Preconditions.checkNotNull(builder.type);
        this.player = Preconditions.checkNotNull(builder.player);
        this.column = Preconditions.checkNotNull(builder.column);
    }


    public void setType(String type) {
		this.type = type;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public String getType() {
        return type;
    }

    public String getPlayer() {
        return player;
    }

    public int  getColumn() {
        return column;
    }
    
    public String toString() {
    	return "type: "+getType() + " ,Player: " + getPlayer() + " ,Column: " + getColumn(); 
    }

    public static class Builder {
        private String type;
        private String player;
        private Integer column;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder player(String player) {
            this.player = player;
            return this;
        }

        public Builder column(Integer column) {
            this.column = column;
            return this;
        }

        public Builder fromPrototype(GetMoveResponse prototype) {
            type = prototype.type;
            player = prototype.player;
            column = prototype.column;
            return this;
        }

        public GetMoveResponse build() {
        	GetMoveResponse resp = new GetMoveResponse();
        	resp.column = this.column;
        	resp.player = this.player;
        	resp.type = this.type;
        	return resp;
        }
    }
}
