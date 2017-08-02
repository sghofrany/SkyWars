package me.iran.skywars.arena.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.iran.skywars.arena.Arena;
import me.iran.skywars.customevents.PlayerLeaveArenaEvent;

public class PlayerLeaveArena implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLeave(PlayerLeaveArenaEvent event) {
		
		Arena arena = event.getArena();
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			if(arena.getPlayers().contains(p.getName())) {
				p.sendMessage(ChatColor.RED.toString() + event.getPlayer().getName() + ChatColor.YELLOW + " has left the match (" + event.getArena().getPlayers().size() + "/12)");
			}
		}
		
	}
	
}
