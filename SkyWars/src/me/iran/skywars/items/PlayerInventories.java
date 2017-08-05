package me.iran.skywars.items;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.iran.skywars.arena.LootManager;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.kits.Kit;
import me.iran.skywars.kits.KitManager;

public class PlayerInventories {

	public void unranked(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked");

		ItemStack grass = new ItemStack(Material.GRASS, 1);
		
		ItemMeta gMeta = grass.getItemMeta();
		
		gMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Solo Unranked");
		
		grass.setItemMeta(gMeta);
		
		inv.setItem(0, grass);
		
		player.openInventory(inv);
	}
	
	public void kits(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits");
		
		if(KitManager.getManager().getKits().size() > 0) {
			
			for(int i = 0; i < KitManager.getManager().getKits().size(); i++) {
				
				Kit kit = KitManager.getManager().getKits().get(i);
				
				if(player.hasPermission(kit.getPermission())) {
					
					ItemStack item = kit.getDisplay();
					
					ItemMeta kitMeta = item.getItemMeta();
					
					kitMeta.setDisplayName(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + kit.getName().toUpperCase());
					
					item.setItemMeta(kitMeta);
					
					inv.setItem(i, item);
				} else {
					
					ItemStack item = new ItemStack(Material.STAINED_GLASS, 1, (short) 14);
					
					ItemMeta kitMeta = item.getItemMeta();
					
					kitMeta.setDisplayName(ChatColor.DARK_RED.toString() + ChatColor.BOLD + kit.getName().toUpperCase());
					
					item.setItemMeta(kitMeta);
					
					inv.setItem(i, item);
					
				}
			}
		}
		
		player.openInventory(inv);
		
	}
	
	public void viewLoot(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GREEN.toString() + ChatColor.BOLD + "Set Loot");
		
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
	
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN + "[Tier 1 Above]" + ChatColor.RED + " [Tier 2 Down]");
		
		item.setItemMeta(meta);
		
		for(int i = 27; i < 36; i++) {
			inv.setItem(i, item);
		}
		
		for(int i = 0; i < LootManager.getLoot().getTier1().length; i++) {
			ItemStack it = LootManager.getLoot().getTier1()[i];
			
			inv.setItem(i, it);
		}
		
		for(int i = 0; i < LootManager.getLoot().getTier2().length; i++) {
			
			ItemStack it = LootManager.getLoot().getTier2()[i];
			
			inv.setItem(i + 36, it);
		}
		
		player.openInventory(inv);
		
	}
	
	public void playersLeftSolo(Player player, Duel duel) {
		
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Players Left");
		
		for(int i = 0; i < duel.getAlive().size(); i++) {
			
			ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			
			ItemMeta hMeta = head.getItemMeta();
			
			hMeta.setDisplayName(ChatColor.GREEN + duel.getAlive().get(i));
			head.setItemMeta(hMeta);
			
			inv.setItem(i, head);
		}
		
		player.openInventory(inv);
		
	}
	
}
