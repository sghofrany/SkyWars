package me.iran.skywars.kits;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.iran.skywars.SkyWars;

public class KitManager {

	private static ArrayList<Kit> kits = new ArrayList<>();
	
	File file = null;
	
	public KitManager () {}
	
	private static KitManager gm;
	
	public static KitManager getManager() {
		
		if(gm == null) {
			gm = new KitManager();
		}
		
		return gm;
		
	}
	
	public void loadKits() {
		
		file = new File(SkyWars.getInstance().getDataFolder(), "kit.yml");
		
		if(file.exists()) {
			
			file = new File(SkyWars.getInstance().getDataFolder(), "kit.yml");
			
			YamlConfiguration gConfig = YamlConfiguration.loadConfiguration(file);
			
			for(String s : gConfig.getConfigurationSection("kits").getKeys(false)) {
				
				String name = gConfig.getString("kits." + s + ".name");
				String permission = gConfig.getString("kits." + s + ".permission");
				
				List<?> items = gConfig.getList("kits." + s + ".inv");
				List<?> armor = gConfig.getList("kits." + s + ".armor");
				
				ItemStack[] arrayItem = items.toArray(new ItemStack[items.size()]);
				ItemStack[] arrayArmor = armor.toArray(new ItemStack[armor.size()]);
				
				ItemStack display = (ItemStack) gConfig.get("kits." + s + ".display");
				
				Kit kit = new Kit(name);
				
				kit.setArmor(arrayArmor);
				kit.setInv(arrayItem);
				kit.setName(name);
				kit.setDisplay(display);
				kit.setPermission(permission);
				
				kits.add(kit);
				
			}
		} else {
			SkyWars.getInstance().getServer().getConsoleSender().sendMessage(ChatColor.RED + "Couldn't find any kits to load in");
		}
	}
	
	public void saveKits() {
		
		file = new File(SkyWars.getInstance().getDataFolder(), "kit.yml");
		
		if(file.exists()) {
			
			YamlConfiguration gConfig = YamlConfiguration.loadConfiguration(file);
			
			ArrayList<ItemStack> invList = new ArrayList<ItemStack>();
			ArrayList<ItemStack> armorList = new ArrayList<ItemStack>();
			
			for(int i = 0; i < kits.size(); i++) {
				String name = kits.get(i).getName();
				
				Kit kit = getKitByName(name);
				
				ItemStack[] inv = kit.getInv();
				ItemStack[] armor = kit.getArmor();
				
				for(int j = 0; j < inv.length; j++) {
					ItemStack item = inv[j];
					if(item != null) {
						invList.add(item);
					}
				}
				
				for(int k = 0; k < armor.length; k++) {
					ItemStack arm = armor[k];
					if(arm != null) {
						armorList.add(arm);
					}
				}
				
				gConfig.set("kits." + name + ".name", kit.getName());
				gConfig.set("kits." + name + ".permission", kit.getPermission());
				gConfig.set("kits." + name + ".inv", invList);
				gConfig.set("kits." + name + ".armor", armorList);
				gConfig.set("kits." + name + ".display", kit.getDisplay());
				
				try {
					gConfig.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			file = new File(SkyWars.getInstance().getDataFolder(), "kit.yml");
			
			YamlConfiguration gConfig = YamlConfiguration.loadConfiguration(file);
			
			ArrayList<ItemStack> invList = new ArrayList<ItemStack>();
			ArrayList<ItemStack> armorList = new ArrayList<ItemStack>();
			
			for(Kit kit : kits) {
				
				String name = kit.getName();
				
				ItemStack[] inv = kit.getInv();
				ItemStack[] armor = kit.getArmor();
				
				for(int j = 0; j < inv.length; j++) {
					ItemStack item = inv[j];
					if(item != null) {
						invList.add(item);
					}
				}
				
				for(int k = 0; k < armor.length; k++) {
					ItemStack arm = armor[k];
					if(arm != null) {
						armorList.add(arm);
					}
				}
				
				gConfig.createSection("kits." + name + ".name");
				gConfig.createSection("kits." + name + ".permission");
				gConfig.createSection("kits." + name + ".inv");
				gConfig.createSection("kits." + name + ".armor");
				gConfig.createSection("kits." + name + ".display");
				
				gConfig.set("kits." + name + ".name", kit.getName());
				gConfig.set("kits." + name + ".permission", kit.getPermission());
				gConfig.set("kits." + name + ".inv", invList);
				gConfig.set("kits." + name + ".armor", armorList);
				gConfig.set("kits." + name + ".display", kit.getDisplay());
				
				try {
					gConfig.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void createKit(Player player, String name) {
		
		if(getKitByName(name) != null) {
			player.sendMessage(ChatColor.RED + "A kit with that name already exists");
			return;
		}
		
		Kit kit = new  Kit(name);
		
		kit.setInv(player.getInventory().getContents());
		kit.setArmor(player.getInventory().getArmorContents());
		
		if(player.getItemInHand().getType() != null) {
			kit.setDisplay(player.getItemInHand());
		}
		
		kits.add(kit);
		
	}
	
	public void deleteKit(Player player, String name) {

		file = new File(SkyWars.getInstance().getDataFolder(), "kit.yml");

		if (file.exists()) {

			YamlConfiguration gConfig = YamlConfiguration.loadConfiguration(file);

			gConfig.set("kits." + name, null);

			try {
				gConfig.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			player.sendMessage(ChatColor.RED + "Couldn't find a kit with the name of " + name);
		}

	}
	
	public Kit getKitByName(String name) {
		for(Kit kit : kits) {
			if(kit.getName().equalsIgnoreCase(name)) {
				return kit;
			}
		}
		
		return null;
	}
	
	
	public ArrayList<Kit> getKits() {
		return kits;
	}
}
