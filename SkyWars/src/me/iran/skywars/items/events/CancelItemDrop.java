package me.iran.skywars.items.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.iran.skywars.SkyWars;

public class CancelItemDrop implements Listener {

	SkyWars plugin;
	
	public CancelItemDrop(SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		
		if(event.getItemDrop() == null) {
			return;
		}
		
		if(event.getItemDrop().getItemStack() == null) {
			return;
		}
		
		if(event.getItemDrop().getItemStack().getItemMeta() == null) {
			return;
		}

		if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == null) {
			return;
		}
		
		if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits")) {
			event.setCancelled(true);
		} else if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.AQUA.toString() + ChatColor.BOLD + "Events")) {
			event.setCancelled(true);
		} else if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Clans")) {
			event.setCancelled(true);
		} else if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.BLUE.toString() + ChatColor.BOLD + "Create Team")) {
			event.setCancelled(true);
		} else if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked Queue")) {
			event.setCancelled(true);
		} else if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.RED.toString() + ChatColor.BOLD + "Ranked Queue")) {
			event.setCancelled(true);
		}
		 
	}
	
}
