package me.iran.skywars.duel;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.iran.skywars.arena.Arena;
import me.iran.skywars.arena.ArenaManager;

public class DuelManager {

	private static ArrayList<Duel> duels = new ArrayList<>();
	
	private static DuelManager manager;
	
	public static DuelManager getManager() {
		
		if(manager == null) {
			manager = new DuelManager();
		}
		
		return manager;
	}
	
	public void unrankedSolo(Arena arena, ArrayList<String> players) {
		
		Duel duel = new Duel(arena);
		
		duel.setPlayers(players);

		ArenaManager.getManager().teleportSolo(duel);
		
		duels.add(duel);
		
	}
	
	public Duel getDuelByArena(Arena arena) {
		
		for(Duel duel : duels) {
			if(duel.getArena().getId().equalsIgnoreCase(arena.getId())) {
				return duel;
			}
		}
		
		return null;
		
	}

	public boolean isPlayerInDuel(Player player) {
		for(Duel duel : duels) {
			if(duel.getAlive().contains(player.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static ArrayList<Duel> getDules() {
		return duels;
	}
	
}
