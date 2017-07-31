package me.iran.skywars.arena;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.iran.skywars.SkyWars;
import me.iran.skywars.duel.Duel;

public class ArenaManager {

	private File file = null;
	
	private static ArrayList<Arena> arenas = new ArrayList<>();
	
	public ArenaManager() {}
	
	private static ArenaManager am;
	
	public static ArenaManager getManager() {
		if(am == null) {
			am = new ArenaManager();
		} 
		return am;
	}
	
	public void loadArenas() {
		
		file = new File(SkyWars.getInstance().getDataFolder(), "arena.yml");
		
		if(file.exists()) {
			
			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			for(String s : config.getConfigurationSection("arena").getKeys(false)) {

				String name = config.getString("arena." + s + ".name");
				String id = config.getString("arena." + s + ".id");
				
				int refill = config.getInt("arena." + s + ".refill");
				
				boolean team = config.getBoolean("arena." + s + ".team");
				
				Arena arena = new Arena(id);
				
				if(config.contains("arena." + s + ".spawn")) {
					
					String world = config.getString("arena." + s + ".world");
					
					for(String l : config.getConfigurationSection("arena." + s + ".spawn").getKeys(false)) {
						
						double x = config.getDouble("arena." + s + ".spawn." + l + ".x");
						double y = config.getDouble("arena." + s + ".spawn." + l + ".y");
						double z = config.getDouble("arena." + s + ".spawn." + l + ".z");
						
						Location loc = new Location(Bukkit.getWorld(world), x, y, z);
						
						arena.getSpawns().add(loc);
						
					}
					
				}

				arena.setName(name);
				arena.setId(id);
				arena.setRefillTimer(refill);
				arena.setTeam(team);
				
				arenas.add(arena);
				
			}
			
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Success] Found arena.yml for SkyWars and loaded in all arenas");
			
		} else {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Error] Couldn't find a arena.yml file for SkyWars");
		}
		
	}
	
	public void saveArenas() {
		
		file = new File(SkyWars.getInstance().getDataFolder(), "arena.yml");
		
		if(!file.exists()) {
			
			file = new File(SkyWars.getInstance().getDataFolder(), "arena.yml");
			
			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			if(arenas.size() > 0) {
				
				for(Arena arena : arenas) {

					config.createSection("arena." + arena.getId() + ".name");
					config.createSection("arena." + arena.getId() + ".id");
					config.createSection("arena." + arena.getId() + ".refill");
					config.createSection("arena." + arena.getId() + ".team");
					
					if(arena.getSpawns().size() > 0) {
						
						for(int i = 0; i < arena.getSpawns().size(); i++) {
							config.createSection("arena." + arena.getId() + ".spawn." + i + ".x");
							config.createSection("arena." + arena.getId() + ".spawn." + i + ".y");
							config.createSection("arena." + arena.getId() + ".spawn." + i + ".z");
							
							config.set("arena." + arena.getId() + ".spawn." + i + ".x", arena.getSpawns().get(i).getX());
							config.set("arena." + arena.getId() + ".spawn." + i + ".y", arena.getSpawns().get(i).getY());
							config.set("arena." + arena.getId() + ".spawn." + i + ".z", arena.getSpawns().get(i).getZ());
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						
						config.createSection("arena." + arena.getId() + ".world");
						config.set("arena." + arena.getId() + ".world", arena.getSpawns().get(0).getWorld().getName());
						
						
					}
					
					config.set("arena." + arena.getId() + ".name", arena.getName());
					config.set("arena." + arena.getId() + ".id", arena.getId());
					config.set("arena." + arena.getId() + ".refill", arena.getRefillTimer());
					config.set("arena." + arena.getId() + ".team", arena.isTeam());
					
				}
				
				try {
					config.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		} else {
		
			YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
			
			for(Arena arena : arenas) {
				
				config.set("arena." + arena.getId() + ".name", arena.getName());
				config.set("arena." + arena.getId() + ".id", arena.getId());
				config.set("arena." + arena.getId() + ".refill", arena.getRefillTimer());
				config.set("arena." + arena.getId() + ".team", arena.isTeam());
				
				config.set("arena." + arena.getId() + ".spawn", null);
				config.set("arena." + arena.getId() + ".chest", null);
				
				if(arena.getSpawns().size() > 0) {
					
					for(int i = 0; i < arena.getSpawns().size(); i++) {
						config.createSection("arena." + arena.getId() + ".spawn." + i + ".x");
						config.createSection("arena." + arena.getId() + ".spawn." + i + ".y");
						config.createSection("arena." + arena.getId() + ".spawn." + i + ".z");
						
						config.set("arena." + arena.getId() + ".spawn." + i + ".x", arena.getSpawns().get(i).getX());
						config.set("arena." + arena.getId() + ".spawn." + i + ".y", arena.getSpawns().get(i).getY());
						config.set("arena." + arena.getId() + ".spawn." + i + ".z", arena.getSpawns().get(i).getZ());
					}
					
					config.createSection("arena." + arena.getId() + ".world");
					config.set("arena." + arena.getId() + ".world", arena.getSpawns().get(0).getWorld().getName());
					
					
				}
				
			}
			
			try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void createArena(Player player, String name) {
		
		Arena arena = new Arena(name);
		
		arenas.add(arena);
		
		player.sendMessage(ChatColor.YELLOW + "Arena " + ChatColor.GOLD + name + ChatColor.YELLOW + " created successfully");
	}
	
	public Arena getArenaByName(String name) {
		
		for(Arena arena : arenas) {
			if(arena.getId().equalsIgnoreCase(name)) {
				return arena;
			}
		}
		
		return null;
	}
	
	
	public void teleportSolo(Duel duel) {
		
		Arena arena = duel.getArena();
		
		for(int i = 0; i < arena.getSpawns().size(); i++) {
			
			Player p = Bukkit.getPlayer(duel.getPlayers().get(i));
			
			if(p != null) {
				p.teleport(arena.getSpawns().get(i));
				p.sendMessage(ChatColor.GREEN + "Teleported to arena " + arena.getName());
			}
			
		}
		
	}
}
