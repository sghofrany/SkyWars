package me.iran.skywars.duel.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.arena.ArenaManager;

public class RespawnEvent implements Listener {

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		
		Player player = event.getPlayer();
		
/*		if(!ArenaManager.getManager().isPlayerSpectating(player)) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), new Runnable() {
				
				public void run() {
					SkyWars.getInstance().teleportSpawn(player);
				}
				
			}, 5);
		}*/
		
	}
	
}
