package me.iran.skywars.arena;

import org.bukkit.scheduler.BukkitRunnable;

import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;

public class ArenaRunnables extends BukkitRunnable {

	public void run() {
		
		for(Arena arena : ArenaManager.getManager().getArenas()) {
			
			if(arena.getTime() != -1) {
				
				if(arena.getTime() > 0) {
					
					arena.setTime(arena.getTime() - 1);
					
					if(arena.getTime() <= 0) {
						
						DuelManager.getManager().unrankedSolo(arena, arena.getPlayers());
						
						arena.setTime(-1);
					}
					
				}
				
			}
			
		}
		
	}
	
}
