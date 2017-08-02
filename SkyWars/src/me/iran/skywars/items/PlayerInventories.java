package me.iran.skywars.items;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.iran.skywars.kits.Kit;
import me.iran.skywars.kits.KitManager;
import me.iran.skywars.utils.Queue;

public class PlayerInventories {

	private Queue queue = new Queue();
	
	public void unranked(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked");

		ItemStack grass = new ItemStack(Material.GRASS, 1);
		
		ItemMeta gMeta = grass.getItemMeta();
		
		gMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Solo Unranked");
		gMeta.setLore(Arrays.asList("", ChatColor.YELLOW + "In Queue: " + queue.getUnrankedSoloQueue().size(), ChatColor.GOLD + "In Game: "));
		
		grass.setItemMeta(gMeta);
		
		inv.setItem(0, grass);
		
		player.openInventory(inv);
	}
	
	public void kits(Player player) {
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits");
		
		if(KitManager.getManager().getKits().size() > 0) {
			
			for(int i = 0; i < KitManager.getManager().getKits().size(); i++) {
				
				Kit kit = KitManager.getManager().getKits().get(i);
				
				ItemStack item = kit.getDisplay();
				
				ItemMeta kitMeta = item.getItemMeta();
				
				kitMeta.setDisplayName(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + kit.getName().toUpperCase());
				
				item.setItemMeta(kitMeta);
				
				inv.setItem(i, item);
				
			}
		}
		
		player.openInventory(inv);
		
	}
	
	
}
