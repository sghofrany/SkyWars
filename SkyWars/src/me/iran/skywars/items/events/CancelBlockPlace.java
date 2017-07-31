package me.iran.skywars.items.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.duel.DuelManager;

public class CancelBlockPlace implements Listener {

	SkyWars plugin;
	
	public CancelBlockPlace(SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		
		if(!DuelManager.getManager().isPlayerInDuel(event.getPlayer())) {
			event.setCancelled(true);
		} 
		
	}
	
}
