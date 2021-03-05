package com._98point6.droptoken.bo;

public class Move {
    private int id;
    private Player player;
    // MOVE, QUIT
    private String typeCode;
    private int column;
    private int colorFlag;

	public int getColorFlag() {
		return colorFlag;
	}

	public void setColorFlag(int colorFlag) {
		this.colorFlag = colorFlag;
	}

	public int getId() {
		return id;
	}

	public void setStep(int step) {
		this.id = step;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Move() {

    }
	
	@Override
	public int hashCode() {
		return (""+getId()+getPlayer()+getColumn()+getTypeCode()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		Move m = (Move) obj;

		return this.id == m.getId() && this.player.equals(player) && this.typeCode.equals(m.getTypeCode()) && this.getColumn() == m.getColumn(); 
	}

    @Override
    public String toString() {
        return ""+getId();
    }
}