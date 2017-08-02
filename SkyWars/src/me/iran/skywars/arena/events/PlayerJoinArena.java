package me.iran.skywars.arena.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.iran.skywars.customevents.PlayerJoinArenaEvent;
import me.iran.skywars.duel.DuelManager;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoinArena implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinArenaEvent event) {
		
		Player player = event.getPlayer();
		
		player.sendMessage(ChatColor.GREEN + "Teleported to arena " + event.getArena().getName());
		
		if(event.getArena().getSpawns().size() - event.getArena().getTempspawn().size() <= 4) {
			
			if(event.getArena().getTime() > 20) {
				event.getArena().setTime(20);
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					
					if(event.getArena().getPlayers().contains(p.getName())) {
						
						p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Match will start in " + ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "20 seconds" + ChatColor.GREEN.toString() + ChatColor.BOLD  + "...");
						
					}
				}
			}
			
		}
		
	}
	
}
