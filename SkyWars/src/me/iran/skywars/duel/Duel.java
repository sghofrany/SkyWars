package me.iran.skywars.duel;

import java.util.ArrayList;

import me.iran.skywars.arena.Arena;

public class Duel {

	private ArrayList<String> players;
	
	private ArrayList<String> alive;
	
	private Arena arena;
	
	private String winner;
	
	public Duel(Arena arena) {
		
		setPlayers(new ArrayList<String>());
		setAlive(new ArrayList<String>());
		
		this.setArena(arena);
	}

	public ArrayList<String> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public ArrayList<String> getAlive() {
		return alive;
	}

	public void setAlive(ArrayList<String> alive) {
		this.alive = alive;
	}
	
}
