package me.iran.skywars.items.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;
import me.iran.skywars.duel.Spectate;
import me.iran.skywars.items.PlayerInventories;

public class InteractWithItemsInHand implements Listener {

	private PlayerInventories inv = new PlayerInventories();

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();

		if (event.getAction() == null) {
			return;
		}

		if (player.getItemInHand() == null) {
			return;
		}

		if (!player.getItemInHand().hasItemMeta()) {
			return;
		}

		if (player.getItemInHand().getItemMeta().getDisplayName() == null) {
			return;
		}

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits")) {
				inv.kits(player);
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA.toString() + ChatColor.BOLD + "Events")) {
				
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Clans")) {
				
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Clans")) {
				
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE.toString() + ChatColor.BOLD + "Create Team")) {
				
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked Queue")) {
				inv.unranked(player);
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED.toString() + ChatColor.BOLD + "Ranked Queue")) {
				
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED.toString() + ChatColor.BOLD + "Leave Match")) {
				
				if(!DuelManager.getManager().isPlayerInDuel(player)) {
					ArenaManager.getManager().leaveArena(player);
				} else {
					player.sendMessage(ChatColor.RED + "Can't leave while in a duel!");
				}
				
			}  else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Leave Spectator Mode")) {
				Spectate.leaveSpectator(player);
			} else if(player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "View Players")) {
				
				if(ArenaManager.getManager().isPlayerSpectating(player)) {
					
					Duel duel = DuelManager.getManager().getDuelByArena(ArenaManager.getManager().getSpectatingArena(player));
					
					inv.playersLeftSolo(player, duel);
					
				}
				
			}
			
		}
		
		
	}
	
}
