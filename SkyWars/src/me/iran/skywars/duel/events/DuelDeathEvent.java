package me.iran.skywars.duel.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;

public class DuelDeathEvent implements Listener {

	SkyWars plugin;
	
	public DuelDeathEvent (SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
	
		event.setDeathMessage(null);
		
		Player player = event.getEntity();
		
		if(DuelManager.getManager().isPlayerInDuel(player)) {
			
			Duel duel = DuelManager.getManager().getDuelByPlayer(player);
			
			if(duel.getAlive().contains(player.getName())) {
				
				duel.getAlive().remove(player.getName());

				if(duel.getAlive().size() == 1) {
					DuelManager.getManager().endUnrankedSolo(Bukkit.getPlayer(duel.getAlive().get(0)));
				}
			
			}
			
		}
		
		player.spigot().respawn();
		
		
	}
	
}
