package me.iran.skywars.arena;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.iran.skywars.SkyWars;
import net.md_5.bungee.api.ChatColor;

public class LootManager {

	File file = null;
	
	private static Loot loot = null;
	
	private static LootManager lm;
	
	public static LootManager getManager() {
		if(lm == null) {
			lm = new LootManager();
		}
		
		return lm;
	}
	
	public void loadLoot() {

		file = new File(SkyWars.getInstance().getDataFolder(), "loot.yml");

		if (file.exists()) {

			file = new File(SkyWars.getInstance().getDataFolder(), "loot.yml");

			YamlConfiguration gConfig = YamlConfiguration.loadConfiguration(file);

			List<?> t1 = gConfig.getList("tier1");

			List<?> t2 = gConfig.getList("tier2");
			
			ItemStack[] arrayItem = null;
			ItemStack[] arrayItem2 = null;
			
			if(t1.size() > 0) {
				arrayItem = t1.toArray(new ItemStack[t1.size()]);
			}
			
			if(t2.size() > 0) {
				arrayItem2 = t2.toArray(new ItemStack[t2.size()]);
			}

			if(arrayItem != null && arrayItem2 != null) {
				setLoot(new Loot(arrayItem, arrayItem2));
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Loot loaded correctly");
			} else {
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Either tier1 or tier2 is null");
			}
			
		} else {

			loot = new Loot();

			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't find any loot");
			
		}

	}
	
	public void saveLoot() {

		file = new File(SkyWars.getInstance().getDataFolder(), "loot.yml");

		if (file.exists()) {

			YamlConfiguration gConfig = YamlConfiguration.loadConfiguration(file);

			ArrayList<ItemStack> tier1 = new ArrayList<ItemStack>();
			ArrayList<ItemStack> tier2 = new ArrayList<ItemStack>();

			ItemStack[] t1 = loot.getTier1();
			ItemStack[] t2 = loot.getTier2();

			for (int i = 0; i < t1.length; i++) {
				ItemStack item = t1[i];
				if (item != null) {
					tier1.add(item);
				}
			}

			for (int j = 0; j < t2.length; j++) {
				ItemStack item = t2[j];
				if (item != null) {
					tier2.add(item);
				}
			}

			gConfig.set("tier1", tier1);
			gConfig.set("tier2", tier2);

			try {
				gConfig.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			
			if(LootManager.getLoot().getTier1().length > 0 && LootManager.getLoot().getTier2().length > 0) {
				file = new File(SkyWars.getInstance().getDataFolder(), "loot.yml");
				
				YamlConfiguration gConfig = YamlConfiguration.loadConfiguration(file);
				
				gConfig.createSection("tier1");
				gConfig.createSection("tier2");
				
				ArrayList<ItemStack> tier1 = new ArrayList<ItemStack>();
				ArrayList<ItemStack> tier2 = new ArrayList<ItemStack>();

				ItemStack[] t1 = loot.getTier1();
				ItemStack[] t2 = loot.getTier2();

				for (int i = 0; i < t1.length; i++) {
					ItemStack item = t1[i];
					if (item != null) {
						tier1.add(item);
					}
				}

				for (int j = 0; j < t2.length; j++) {
					ItemStack item = t2[j];
					if (item != null) {
						tier2.add(item);
					}
				}

				gConfig.set("tier1", tier1);
				gConfig.set("tier2", tier2);

				try {
					gConfig.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void setLoot(Player player, ItemStack[] tier1, ItemStack[] tier2) {
		
		loot.setTier1(tier1);
		loot.setTier2(tier2);
	}

	public static Loot getLoot() {
		return loot;
	}

	public static void setLoot(Loot loot) {
		LootManager.loot = loot;
	}
	
}
