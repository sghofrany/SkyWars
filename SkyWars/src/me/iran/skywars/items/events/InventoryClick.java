package me.iran.skywars.items.events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.arena.LootManager;
import me.iran.skywars.duel.DuelManager;
import me.iran.skywars.kits.Kit;
import me.iran.skywars.kits.KitManager;

public class InventoryClick implements Listener {

	private static HashMap<String, Kit> chosenKit = new HashMap<>();
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		
		if(!DuelManager.getManager().isPlayerInDuel(player) && !player.hasPermission("skywars.staff")) {
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

		if(event.getClickedInventory().getTitle().equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "Set Loot")) {
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "[Tier 1 Above]" + ChatColor.RED + " [Tier 2 Down]")) {
				event.setCancelled(true);
			}
		}
		
		if(event.getClickedInventory().getTitle().equals(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked")) {
			event.setCancelled(true);
			
			if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "Solo Unranked")) {
				
				
				if(!DuelManager.getManager().isPlayerInDuel(player)) {
					ArenaManager.getManager().teleportToRandomArena(player);
					player.closeInventory();
				} else {
					player.closeInventory();
					player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Can't do this while in a match!");
				}
			}
			
		}
		
		if(event.getClickedInventory().getTitle().equals(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits")) {
			
			Kit kit = KitManager.getManager().getKitByName(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
			
			if(!DuelManager.getManager().isPlayerInDuel(player)) {
			
				if(ArenaManager.getManager().isPlayerInArena(player)) {
					if(kit != null) {
						
						if(player.hasPermission(kit.getPermission())) {
							chosenKit.put(player.getName(), kit);
							player.sendMessage(ChatColor.YELLOW.toString()  + ChatColor.BOLD + "You have chosen kit " + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + kit.getName().toUpperCase());
							player.closeInventory();
						} else {
							player.sendMessage(ChatColor.RED + "You don't have permission to us this kit, visit buycraft.erid.com");
						}

					}
				}

			} 
			
			if(DuelManager.getManager().isPlayerInDuel(player)) {
				
				if(player.hasPermission(kit.getPermission())) {
					chosenKit.put(player.getName(), kit);
					player.sendMessage(ChatColor.YELLOW.toString()  + ChatColor.BOLD + "You have chosen kit " + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + kit.getName().toUpperCase());
		
					player.getInventory().setContents(kit.getInv());
					player.getInventory().setArmorContents(kit.getArmor());
					player.closeInventory();
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to us this kit, visit buycraft.erid.com");
				}
				
			}
			
			if(!DuelManager.getManager().isPlayerInDuel(player)) {
				
				if(!ArenaManager.getManager().isPlayerInArena(player)) {
					if(kit != null) {
						if(player.hasPermission(kit.getPermission())) {
							player.sendMessage(ChatColor.GREEN.toString()  + ChatColor.BOLD + "You already own the kit " + ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + kit.getName().toUpperCase());
							player.closeInventory();
						} else {
							player.sendMessage(ChatColor.RED + "You don't have permission to us this kit, visit buycraft.erid.com");
						}

					}
				}

			} 
			
			event.setCancelled(true);
			
		}
		
	}

/*	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		
		Player player = (Player) event.getPlayer();
		
		if(event.getInventory().getTitle().equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "Set Loot")) {
			
			ArrayList<ItemStack> tier1 = new ArrayList<>();
			
			for(int i = 0; i < 10; i++) {
				if(event.getInventory().getContents()[i] != null) {
					tier1.add(event.getInventory().getContents()[i]);
				}
			}
			
			ArrayList<ItemStack> tier2 = new ArrayList<>();
			
			for(int i = 37; i < 54; i++) {
				if(event.getInventory().getContents()[i] != null) {
					tier2.add(event.getInventory().getContents()[i]);
				}
			}
			
			ItemStack[] t1 = tier1.toArray(new ItemStack[tier1.size()]);
			ItemStack[] t2 = tier1.toArray(new ItemStack[tier2.size()]);
			
			LootManager.getManager().setLoot(player, t1, t2);
			
			player.sendMessage(ChatColor.GREEN + "Loot set");
		}
		
	}*/
	
	public HashMap<String, Kit> getChosenKit() {
		return chosenKit;
	}

	public void setChosenKit(HashMap<String, Kit> chosenKit) {
		InventoryClick.chosenKit = chosenKit;
	}
	
}
