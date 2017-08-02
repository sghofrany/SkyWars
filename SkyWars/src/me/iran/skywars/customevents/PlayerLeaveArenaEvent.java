package me.iran.skywars.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.iran.skywars.arena.Arena;

public class PlayerLeaveArenaEvent extends Event {
	
	private Player player;
	private Arena arena;
	
	public PlayerLeaveArenaEvent(Player player, Arena arena) {
		this.setPlayer(player);
		this.setArena(arena);
	}
	
	private static final HandlerList handlers = new HandlerList();
    
    public HandlerList getHandlers() {
        return handlers;
    }
     
    public static HandlerList getHandlerList() {
        return handlers;
    }

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
}
