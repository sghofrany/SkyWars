package me.iran.skywars.duel.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.iran.skywars.duel.DuelManager;


public class PickItemUp implements Listener {

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		
		Player player = event.getPlayer();
		
		if(!DuelManager.getManager().isPlayerInDuel(player) && !player.hasPermission("skywars.staff")) {
			event.setCancelled(true);
		}
		
	}
	
}
