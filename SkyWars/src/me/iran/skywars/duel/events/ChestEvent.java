package me.iran.skywars.duel.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.iran.skywars.arena.LootManager;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;

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
					event.setCancelled(true);
					return;
				}
				
				Duel duel = DuelManager.getManager().getDuelByPlayer(player);
				
				if(duel.getArena().getChests().contains(event.getClickedBlock().getLocation())) {
					return;
				}
				
				chest.getBlockInventory().clear();
				
				int fill = new Random().nextInt(5) + 1;

				for(int i = 0; i < fill; i++) {
					
					int pick = new Random().nextInt(LootManager.getLoot().getTier1().length);
					
					ItemStack item = LootManager.getLoot().getTier1()[pick];
					
					if(!doesContain(item, chest.getBlockInventory())) {
						chest.getBlockInventory().addItem(item);
					} else {
						fill--;
					}

				}
				
				duel.getArena().getChests().add(event.getClickedBlock().getLocation());
				
			}

			if(event.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
				
				Chest chest = (Chest) event.getClickedBlock().getState();
				
				if(!DuelManager.getManager().isPlayerInDuel(player)) {
					event.setCancelled(true);
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
					
					ItemStack item = LootManager.getLoot().getTier2()[pick];
					
					if(!doesContain(item, chest.getBlockInventory())) {
						chest.getBlockInventory().addItem(item);
					} else {
						fill--;
					}
					
				}
				
				duel.getArena().getChests().add(event.getClickedBlock().getLocation());
				
			}
			
		}
		
	}
	
	private boolean doesContain(ItemStack item, Inventory inv) {
		
		if(inv.containsAtLeast(item, 1)) {
			return true;
		}
		
		return false;
	}
	
}
