package me.iran.skywars.items.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.iran.skywars.SkyWars;

public class CancelDrop implements Listener {

	SkyWars plugin;
	
	public CancelDrop(SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		
		Player player = event.getPlayer();
		
		System.out.println("drop");
		
		if(player.getItemInHand() == null) {
			return;
		}
		
		System.out.println("1");
		
		if(!player.getItemInHand().hasItemMeta()) {
			return;
		}
		
		System.out.println("2");
		
		if(!player.getItemInHand().getItemMeta().hasDisplayName()) {
			return;
		}
		
		System.out.println("3");
		
		if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits")) {
			event.setCancelled(true);
		} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA.toString() + ChatColor.BOLD + "Events")) {
			event.setCancelled(true);
		} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Clans")) {
			event.setCancelled(true);
		} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE.toString() + ChatColor.BOLD + "Create Team")) {
			event.setCancelled(true);
		} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked Queue")) {
			event.setCancelled(true);
		} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED.toString() + ChatColor.BOLD + "Ranked Queue")) {
			event.setCancelled(true);
		}
		
		System.out.println("4");
		 
	}
	
}
