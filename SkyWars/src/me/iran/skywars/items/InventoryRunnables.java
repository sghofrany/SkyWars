package me.iran.skywars.items;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.iran.skywars.SkyWars;
import me.iran.skywars.utils.Queue;

public class InventoryRunnables extends BukkitRunnable {

	SkyWars plugin;
	
	private Queue queue = new Queue();
	
	public InventoryRunnables(SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			
			if(player.getOpenInventory().getTitle().equals(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked")) {
				
				ItemStack grass = new ItemStack(Material.GRASS, 1);
				
				ItemMeta gMeta = grass.getItemMeta();
				
				gMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Solo Unranked");
				gMeta.setLore(Arrays.asList("", ChatColor.YELLOW + "In Queue: " + queue.getUnrankedSoloQueue().size(), ChatColor.GOLD + "In Game: "));
				
				grass.setItemMeta(gMeta);
				
				player.getOpenInventory().setItem(0, grass);
			}
			
		}
		
	}
}
