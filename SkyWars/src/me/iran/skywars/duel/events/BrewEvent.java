package me.iran.skywars.duel.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.iran.skywars.items.PlayerInventories;

public class BrewEvent implements Listener {

	private PlayerInventories inv = new PlayerInventories();
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if(event.getAction() == null) {
			return;
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(event.getClickedBlock().getType() == Material.BREWING_STAND) {
				event.setCancelled(true);
				inv.brew(player);
				
			}
		}
		
	}
	
}
