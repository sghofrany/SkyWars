package me.iran.skywars.items.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.duel.DuelManager;

public class CancelInventoryClick implements Listener {

	SkyWars plugin;
	
	public CancelInventoryClick (SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		
		if(DuelManager.getManager().isPlayerInDuel(player)) {
			event.setCancelled(true);
		}
		
	}
	
}
