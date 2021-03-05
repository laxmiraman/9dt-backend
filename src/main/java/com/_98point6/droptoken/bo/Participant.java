package com._98point6.droptoken.bo;

import java.util.HashMap;
import java.util.Map;

public class Participant {

	private int id;
	private Map<Player, Integer> players = new HashMap();

	public Map<Player, Integer> getPlayers() {
		return players;
	}

	public void setPlayers(Map<Player, Integer> _players) {
		this.players = _players;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addPlayer(Player _player, int colorCode) {
		players.put(_player, colorCode);
	}

	public Participant() {

	}

	@Override
	public String toString() {
		return "" + getId();
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		Participant p = (Participant) obj;
		return this.id == p.getId();
	}

}