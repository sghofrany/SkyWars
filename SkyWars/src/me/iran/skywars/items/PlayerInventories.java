package me.iran.skywars.items;


import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

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
	
	public void brew(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED.toString() + ChatColor.BOLD + "Brew");

		Potion speed = new Potion(PotionType.SPEED, 2);
		
		Potion hpSplash = new Potion(PotionType.INSTANT_HEAL, 2);
		hpSplash.setSplash(true);
		
		Potion hpDrink = new Potion(PotionType.INSTANT_HEAL, 2);
		hpDrink.setSplash(false);

		ItemStack s = speed.toItemStack(1);
		ItemStack hps = hpSplash.toItemStack(1);
		ItemStack hpd = hpDrink.toItemStack(1);
		
		ItemMeta sMeta = s.getItemMeta();
		ItemMeta hpsMeta = hps.getItemMeta();
		ItemMeta hpdMeta = hpd.getItemMeta();
		
		sMeta.setDisplayName(ChatColor.AQUA.toString() + "Speed 2");
		hpsMeta.setDisplayName(ChatColor.RED.toString() + "Splash Health Potion 2");
		hpdMeta.setDisplayName(ChatColor.RED.toString() + "Drinkable Health Potion 2");
		
		sMeta.setLore(Arrays.asList(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "------------------------", ChatColor.GRAY + "1x Netherwart", ChatColor.GRAY + "1x Sugar", ChatColor.GRAY + "1x Glowstone Dust", ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "------------------------"));
		hpsMeta.setLore(Arrays.asList(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "------------------------", ChatColor.GRAY + "1x Netherwart", ChatColor.GRAY + "1x Glistering Melon", ChatColor.GRAY + "1x Glowstone Dust", ChatColor.GRAY + "1x Gun Powder",ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "------------------------"));
		hpdMeta.setLore(Arrays.asList(ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "------------------------", ChatColor.GRAY + "1x Netherwart", ChatColor.GRAY + "1x Glistering Melon", ChatColor.GRAY + "1x Glowstone Dust",ChatColor.GOLD.toString() + ChatColor.STRIKETHROUGH + "------------------------"));
		
		s.setItemMeta(sMeta);
		hps.setItemMeta(hpsMeta);
		hpd.setItemMeta(hpdMeta);
		
		inv.setItem(1, s);
		inv.setItem(4, hps);
		inv.setItem(7, hpd);
		
		player.openInventory(inv);
	}
	
}
