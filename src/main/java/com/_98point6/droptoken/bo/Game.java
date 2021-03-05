package com._98point6.droptoken.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Game {

	private String id;
	// DONE, IN_PROGRESS
	private String state;
	private int columns;
	private int rows;
	private Timestamp startTS;
	private Timestamp endTS;
	private Participant participant;
	private String winner;

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getStartTS() {
		return startTS;
	}

	public void setStartTS(Timestamp startTS) {
		this.startTS = startTS;
	}

	public Timestamp getEndTS() {
		return endTS;
	}

	public void setEndTS(Timestamp endTS) {
		this.endTS = endTS;
	}

	public Participant getParticipant() {
		return this.participant;
	}

	public void setParticipant(Participant _participant) {
		this.participant = _participant;
	}

	public Game() {

	}

	public Game(String _id) {
		this.id = _id;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		Game g = (Game) obj;

		return this.id.equals(g.getId());
	}

	@Override
	public String toString() {
		return getId();
	}
}