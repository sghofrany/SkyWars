package me.iran.skywars.kits;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitManager {

	private static ArrayList<Kit> kits = new ArrayList<>();
	
	public KitManager () {}
	
	private static KitManager gm;
	
	public static KitManager getManager() {
		
		if(gm == null) {
			gm = new KitManager();
		}
		
		return gm;
		
	}
	
	public void createKit(Player player, String name) {
		
		if(getKitByName(name) != null) {
			player.sendMessage(ChatColor.RED + "Gametype with that name already exists");
			return;
		}
		
		Kit kit = new  Kit(name);
		
		kits.add(kit);
		
	}
	
	public Kit getKitByName(String name) {
		for(Kit kit : kits) {
			if(kit.getName().equalsIgnoreCase(name)) {
				return kit;
			}
		}
		
		return null;
	}
	
}
