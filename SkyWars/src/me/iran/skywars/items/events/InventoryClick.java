package me.iran.skywars.items.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.duel.DuelManager;
import me.iran.skywars.utils.Queue;

public class InventoryClick implements Listener {

	SkyWars plugin;
	
	private Queue queue = new Queue();
	
	public InventoryClick (SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		
		if(!DuelManager.getManager().isPlayerInDuel(player)) {
			event.setCancelled(true);
		}
		
		if(event.getClickedInventory() == null) {
			return;
		}
		
		if(event.getClickedInventory().getTitle() == null) {
			return;
		}
		
		if(event.getCurrentItem() == null) {
			return;
		}
		
		if(!event.getCurrentItem().hasItemMeta()) {
			return;
		}
		
		if(event.getCurrentItem().getItemMeta().getDisplayName() == null) {
			return;
		}

		if(event.getClickedInventory().getTitle().equals(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked")) {
			
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "Solo Unranked")) {
				event.setCancelled(true);
				
				if(!DuelManager.getManager().isPlayerInDuel(player)) {
					ArenaManager.getManager().teleportToRandomArena(player);
					player.closeInventory();
				} else {
					player.closeInventory();
					player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Can't do this while in a match!");
				}
			}
			
		}
		
	}
	
}
