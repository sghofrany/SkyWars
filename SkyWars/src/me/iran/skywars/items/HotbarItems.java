package me.iran.skywars.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HotbarItems {

	public void defaultItems(Player player) {
		
		ItemStack book = new ItemStack(Material.BOOK, 1);
		ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
		ItemStack star = new ItemStack(Material.NETHER_STAR, 1);
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		ItemStack isword = new ItemStack(Material.IRON_SWORD, 1);
		ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD, 1);
		
		ItemMeta bMeta = book.getItemMeta();
		ItemMeta dMeta = diamond.getItemMeta();
		ItemMeta sMeta = star.getItemMeta();
		ItemMeta hMeta = head.getItemMeta();
		ItemMeta iMeta = isword.getItemMeta();
		ItemMeta dsMeta = dsword.getItemMeta();
		
		bMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits");
		dMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Events");
		sMeta.setDisplayName(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Clans");
		hMeta.setDisplayName(ChatColor.BLUE.toString() + ChatColor.BOLD + "Create Team");
		iMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Unranked Queue");
		dsMeta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Ranked Queue");
		
		book.setItemMeta(bMeta);
		diamond.setItemMeta(dMeta);
		star.setItemMeta(sMeta);
		head.setItemMeta(hMeta);
		isword.setItemMeta(iMeta);
		dsword.setItemMeta(dsMeta);
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		player.getInventory().setItem(0, book);
		player.getInventory().setItem(1, diamond);
		player.getInventory().setItem(3, star);
		player.getInventory().setItem(5, head);
		player.getInventory().setItem(7, isword);
		player.getInventory().setItem(8, dsword);
		
		player.updateInventory();
	}

	public void joinLobby(Player player) {
		
		ItemStack book = new ItemStack(Material.BOOK, 1);
		ItemStack red = new ItemStack(Material.REDSTONE, 1);
		
		ItemMeta bMeta = book.getItemMeta();
		ItemMeta rMeta = red.getItemMeta();
		
		bMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits");
		rMeta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Leave Match");
		
		book.setItemMeta(bMeta);
		red.setItemMeta(rMeta);
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		player.getInventory().setItem(0, book);
		player.getInventory().setItem(8, red);
	}
	
	public void kit(Player player) {
		
		ItemStack book = new ItemStack(Material.BOOK, 1);
		
		ItemMeta bMeta = book.getItemMeta();
		
		bMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kits");
		
		book.setItemMeta(bMeta);
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		player.getInventory().setItem(0, book);
	}
	
}
