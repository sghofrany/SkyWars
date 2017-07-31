package me.iran.skywars.arena;

import java.util.ArrayList;

import org.bukkit.Location;

public class Arena {

	private ArrayList<Location> spawns;
	private ArrayList<Location> chests;
	private ArrayList<Location> blocks;
	
	private ArrayList<String> spectators;
	
	private String name;
	private String id;
	
	private boolean team;
	
	private int refillTimer;
	
	public Arena (String id) {
		
		this.id = id;
		this.name = id;
		
		setSpawns(new ArrayList<>());
		setChests(new ArrayList<>());
		setSpectators(new ArrayList<>());
		setBlocks(new ArrayList<>());
		
		team = false;
		
		setRefillTimer(120);
	}

	public ArrayList<Location> getSpawns() {
		return spawns;
	}

	public void setSpawns(ArrayList<Location> spawns) {
		this.spawns = spawns;
	}

	public ArrayList<Location> getChests() {
		return chests;
	}

	public void setChests(ArrayList<Location> chests) {
		this.chests = chests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTeam() {
		return team;
	}

	public void setTeam(boolean team) {
		this.team = team;
	}

	public int getRefillTimer() {
		return refillTimer;
	}

	public void setRefillTimer(int refillTimer) {
		this.refillTimer = refillTimer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getSpectators() {
		return spectators;
	}

	public void setSpectators(ArrayList<String> spectators) {
		this.spectators = spectators;
	}

	public ArrayList<Location> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Location> blocks) {
		this.blocks = blocks;
	}
	
}
