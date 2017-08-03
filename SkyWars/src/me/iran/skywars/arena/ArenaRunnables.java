package me.iran.skywars.arena;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.iran.skywars.duel.DuelManager;
import net.md_5.bungee.api.ChatColor;

public class ArenaRunnables extends BukkitRunnable {

	@SuppressWarnings("deprecation")
	public void run() {
		
		for(Arena arena : ArenaManager.getManager().getArenas()) {
			
			if(arena.getTime() != -1) {
				
				if(arena.getTime() > 0) {
					
					arena.setTime(arena.getTime() - 1);
					
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						
						if(arena.getPlayers().contains(p.getName())) {
							
							if(arena.getTime() == 15) {
								p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Match will start in " + ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "15 seconds" + ChatColor.GREEN.toString() + ChatColor.BOLD  + "...");
							} else if(arena.getTime() == 10) {
								p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Match will start in " + ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "10 seconds" + ChatColor.GREEN.toString() + ChatColor.BOLD  + "...");
							} else if(arena.getTime() <= 5 && arena.getTime() > 0) {
								p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Match will start in " + ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + arena.getTime() + " seconds" + ChatColor.GREEN.toString() + ChatColor.BOLD  + "...");
							} 
						}
					}
					
					if(arena.getTime() <= 0) {
						
						if(arena.getPlayers().size() >= arena.getMin() && arena.getPlayers().size() > 1) {
							DuelManager.getManager().unrankedSolo(arena, arena.getPlayers());
							
							arena.setTime(-1);
						} else {
							
							arena.setTime(120);
							
							for(Player p : Bukkit.getServer().getOnlinePlayers()) {
								
								if(arena.getPlayers().contains(p.getName())) {
									
									p.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Not enough players to start a match, time reset to 120 seconds");
								}
							}
							
						}

					}
					
				}
				
			}
			
		}
		
	}
	
}
