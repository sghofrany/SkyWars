package me.iran.skywars.arena.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.arena.Arena;
import me.iran.skywars.arena.cmd.ArenaCommands;
import net.md_5.bungee.api.ChatColor;

public class EditEvents implements Listener {

	SkyWars plugin;
	
	public EditEvents (SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if(event.getAction() == null) {
			return;
		}
		
		if(player.getItemInHand().getType() == null) {
			return;
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(ArenaCommands.getEdit().containsKey(player.getName())) {
				
				Arena arena = ArenaCommands.getEdit().get(player.getName());
				
				if(player.getItemInHand().getType() == Material.SPONGE) {
					
					if(!arena.getSpawns().contains(event.getClickedBlock().getLocation())) {
						
						arena.getSpawns().add(event.getClickedBlock().getLocation());
						
						player.sendMessage(ChatColor.GOLD + "Spawn location added " + arena.getSpawns().size());
						
						event.setCancelled(true);
						
					} else {
						player.sendMessage(ChatColor.RED + "That location is already set, left click it to unset!");
					}
					
				}
				
			}
		}
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			if(ArenaCommands.getEdit().containsKey(player.getName())) {
				
				Arena arena = ArenaCommands.getEdit().get(player.getName());
				
				if(player.getItemInHand().getType() == Material.SPONGE) {
					
					if(arena.getSpawns().contains(event.getClickedBlock().getLocation())) {
						
						arena.getSpawns().remove(event.getClickedBlock().getLocation());
						
						player.sendMessage(ChatColor.RED + "Removed this spawn location from the list");
						
						event.setCancelled(true);
						
					} else {
						player.sendMessage(ChatColor.RED + "That location is not in the list");
					}
					
				}
				
			}
		}
		
	}
	
}
