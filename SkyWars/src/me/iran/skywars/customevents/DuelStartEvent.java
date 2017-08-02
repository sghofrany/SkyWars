package me.iran.skywars.customevents;

import java.util.ArrayList;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.iran.skywars.arena.Arena;
import me.iran.skywars.duel.Duel;

public class DuelStartEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
    
	private Duel duel;
	private ArrayList<String> players;
	private Arena arena;

	public DuelStartEvent(ArrayList<String> players, Duel duel, Arena arena) {
		this.setDuel(duel);
		this.players = players;
		this.arena= arena;
	}
	
    public HandlerList getHandlers() {
        return handlers;
    }
     
    public static HandlerList getHandlerList() {
        return handlers;
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

	public Duel getDuel() {
		return duel;
	}

	public void setDuel(Duel duel) {
		this.duel = duel;
	}
	
}
