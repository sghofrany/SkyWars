package me.iran.skywars.duel;

import java.util.ArrayList;

import me.iran.skywars.arena.Arena;
import me.iran.skywars.kits.Kit;

public class Duel {

	private ArrayList<String> alive;
	
	private Arena arena;
	
	private String winner;
	
	private int time;
	
	public Duel(Arena arena) {
		
		setAlive(new ArrayList<String>());
		setTime(0);
		
		this.setArena(arena);
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
	
}
