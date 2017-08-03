package me.iran.skywars.duel.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.iran.skywars.arena.LootManager;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;
import net.md_5.bungee.api.ChatColor;

public class ChestEvent implements Listener {

	@EventHandler
	public void onOpen(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if(event.getAction() == null) {
			return;
		}
		
		if(event.getClickedBlock() == null) {
			return;
		}
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(event.getClickedBlock().getType() == Material.CHEST) {
				
				Chest chest = (Chest) event.getClickedBlock().getState();
				
				if(!DuelManager.getManager().isPlayerInDuel(player)) {
					player.sendMessage(ChatColor.RED + "Can't open chests unless you are in a game!");
					return;
				}
				
				Duel duel = DuelManager.getManager().getDuelByPlayer(player);
				
				if(duel.getArena().getChests().contains(event.getClickedBlock().getLocation())) {
					return;
				}
				
				chest.getBlockInventory().clear();
				
				int fill = new Random().nextInt(6);
				
				for(int i = 0; i < fill; i++) {
					int pick = new Random().nextInt(LootManager.getLoot().getTier1().length);
					
					chest.getBlockInventory().addItem(LootManager.getLoot().getTier1()[pick]);
					
				}
				
				duel.getArena().getChests().add(event.getClickedBlock().getLocation());
				
			}

			if(event.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
				
				Chest chest = (Chest) event.getClickedBlock().getState();
				
				if(!DuelManager.getManager().isPlayerInDuel(player)) {
					player.sendMessage(ChatColor.RED + "Can't open chests unless you are in a game!");
					return;
				}
				
				Duel duel = DuelManager.getManager().getDuelByPlayer(player);
				
				if(duel.getArena().getChests().contains(event.getClickedBlock().getLocation())) {
					return;
				}
				
				chest.getBlockInventory().clear();
				
				int fill = new Random().nextInt(6);
				
				for(int i = 0; i < fill; i++) {
					int pick = new Random().nextInt(LootManager.getLoot().getTier2().length);
					
					chest.getBlockInventory().addItem(LootManager.getLoot().getTier2()[pick]);
					
				}
				
				duel.getArena().getChests().add(event.getClickedBlock().getLocation());
				
			}
			
		}
		
	}
	
}
