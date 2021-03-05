package com._98point6.droptoken.bo;

public class Player{

    private String nickName;
    	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String _nickName) {
		this.nickName = _nickName;
	}

	public Player() {}

	public Player(String name) {
		this.nickName = name;
	}

    @Override
    public String toString() {
        return getNickName();
    }
    
	@Override
	public int hashCode() {
		return getNickName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		Player p = (Player) obj;

		return this.nickName.equals(p.getNickName()); 
	}

}