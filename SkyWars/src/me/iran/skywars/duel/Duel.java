package me.iran.skywars.duel;

import java.util.ArrayList;

import me.iran.skywars.arena.Arena;
import me.iran.skywars.kits.Kit;

public class Duel {

	private ArrayList<String> players;
	
	private ArrayList<String> alive;
	
	private Arena arena;
	
	private String winner;
	
	private int time;
	
	private Kit kit;
	
	public Duel(Arena arena) {
		
		setPlayers(new ArrayList<String>());
		setAlive(new ArrayList<String>());
		setTime(0);
		
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	
}
