package me.iran.skywars.arena;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.iran.skywars.SkyWars;
import me.iran.skywars.customevents.PlayerJoinArenaEvent;
import me.iran.skywars.customevents.PlayerLeaveArenaEvent;
import me.iran.skywars.items.HotbarItems;

public class ArenaManager {

	private File file = null;
	
	private static ArrayList<Arena> arenas = new ArrayList<>();
	private static ArrayList<Arena> available = new ArrayList<>();
	
	private HotbarItems items = new HotbarItems();
	
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
						
						float pitch = (float) config.getDouble("arena." + s + ".spawn." + l + ".pitch");
						float yaw = (float) config.getDouble("arena." + s + ".spawn." + l + ".yaw");
						
						Location loc = new Location(Bukkit.getWorld(world), x, y, z);
						
						loc.setPitch(pitch);
						loc.setYaw(yaw);
						
						arena.getSpawns().add(loc);
						arena.getTempspawn().add(loc);
					}

					arena.setWorld(world);
					
				}
				
			
				if (config.contains("arena." + s + ".lobby")) {

					double x = config.getDouble("arena." + s + ".lobby.x");
					double y = config.getDouble("arena." + s + ".lobby.y");
					double z = config.getDouble("arena." + s + ".lobby.z");
					float pitch = (float) config.getDouble("arena." + s + ".lobby.pitch");
					float yaw = (float) config.getDouble("arena." + s + ".lobby.yaw");

					Location loc = new Location(Bukkit.getWorld(arena.getWorld()), x, y, z);

					loc.setPitch(pitch);
					loc.setYaw(yaw);
					
					arena.setLobby(loc);

				}

				arena.setName(name);
				arena.setId(id);
				arena.setRefillTimer(refill);
				arena.setTeam(team);
				
				arenas.add(arena);
				available.add(arena);
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
							config.createSection("arena." + arena.getId() + ".spawn." + i + ".pitch");
							config.createSection("arena." + arena.getId() + ".spawn." + i + ".yaw");
							
							config.set("arena." + arena.getId() + ".spawn." + i + ".x", arena.getSpawns().get(i).getX());
							config.set("arena." + arena.getId() + ".spawn." + i + ".y", arena.getSpawns().get(i).getY());
							config.set("arena." + arena.getId() + ".spawn." + i + ".z", arena.getSpawns().get(i).getZ());
							config.set("arena." + arena.getId() + ".spawn." + i + ".pitch", arena.getSpawns().get(i).getPitch());
							config.set("arena." + arena.getId() + ".spawn." + i + ".yaw", arena.getSpawns().get(i).getYaw());
							
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						
						config.createSection("arena." + arena.getId() + ".world");
						config.set("arena." + arena.getId() + ".world", arena.getSpawns().get(0).getWorld().getName());
						
						
					}
					
					if(arena.getLobby() != null) {
						
						config.createSection("arena." + arena.getId() + ".lobby.x");
						config.createSection("arena." + arena.getId() + ".lobby.y");
						config.createSection("arena." + arena.getId() + ".lobby.z");
						config.createSection("arena." + arena.getId() + ".lobby.pitch");
						config.createSection("arena." + arena.getId() + ".lobby.yaw");
						
						config.set("arena." + arena.getId() + ".lobby.x", arena.getLobby().getX());
						config.set("arena." + arena.getId() + ".lobby.y", arena.getLobby().getY());
						config.set("arena." + arena.getId() + ".lobby.z", arena.getLobby().getZ());
						config.set("arena." + arena.getId() + ".lobby.pitch", arena.getLobby().getPitch());
						config.set("arena." + arena.getId() + ".lobby.yaw", arena.getLobby().getYaw());
						
						try {
							config.save(file);
						} catch (IOException e) {
							e.printStackTrace();
						}
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
				
				if(arena.getSpawns().size() > 0) {
					
					config.set("arena." + arena.getId() + ".spawn", null);
					
					for(int i = 0; i < arena.getSpawns().size(); i++) {
						
						config.createSection("arena." + arena.getId() + ".spawn." + i + ".x");
						config.createSection("arena." + arena.getId() + ".spawn." + i + ".y");
						config.createSection("arena." + arena.getId() + ".spawn." + i + ".z");
						config.createSection("arena." + arena.getId() + ".spawn." + i + ".pitch");
						config.createSection("arena." + arena.getId() + ".spawn." + i + ".yaw");
						
						config.set("arena." + arena.getId() + ".spawn." + i + ".x", arena.getSpawns().get(i).getX());
						config.set("arena." + arena.getId() + ".spawn." + i + ".y", arena.getSpawns().get(i).getY());
						config.set("arena." + arena.getId() + ".spawn." + i + ".z", arena.getSpawns().get(i).getZ());
						config.set("arena." + arena.getId() + ".spawn." + i + ".pitch", arena.getSpawns().get(i).getPitch());
						config.set("arena." + arena.getId() + ".spawn." + i + ".yaw", arena.getSpawns().get(i).getYaw());
						
					}
					
					config.createSection("arena." + arena.getId() + ".world");
					config.set("arena." + arena.getId() + ".world", arena.getSpawns().get(0).getWorld().getName());
					
					try {
						config.save(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
				if(arena.getLobby() != null) {
					
					config.set("arena." + arena.getId() + ".lobby", null);
					
					config.createSection("arena." + arena.getId() + ".lobby.x");
					config.createSection("arena." + arena.getId() + ".lobby.y");
					config.createSection("arena." + arena.getId() + ".lobby.z");
					config.createSection("arena." + arena.getId() + ".lobby.pitch");
					config.createSection("arena." + arena.getId() + ".lobby.yaw");
					
					config.set("arena." + arena.getId() + ".lobby.x", arena.getLobby().getX());
					config.set("arena." + arena.getId() + ".lobby.y", arena.getLobby().getY());
					config.set("arena." + arena.getId() + ".lobby.z", arena.getLobby().getZ());
					config.set("arena." + arena.getId() + ".lobby.pitch", arena.getLobby().getPitch());
					config.set("arena." + arena.getId() + ".lobby.yaw", arena.getLobby().getYaw());
					
					try {
						config.save(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
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
		available.add(arena);
		
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
	
	public Arena getArenaByPlayer(Player player) {
		
		for(Arena arena : arenas) {
			if(arena.getPlayers().contains(player.getName())) {
				return arena;
			}
		}
		
		return null;
	}
	
	public boolean isPlayerInArena(Player player) {
		
		for(Arena arena : arenas) {
			if(arena.getPlayers().contains(player.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public void teleportToRandomArena(Player player) {
		
		if(available.size() > 0) {
			for(Arena arena : available) {
				if(arena.getTime() != -1 && arena.getTime() > 0 && !arena.isTeam()) {
					
					if(arena.getPlayers().size() > 0 && arena.getPlayers().size() < arena.getSpawns().size()) {
						
						if(!arena.getPlayers().contains(player.getName())) {
							
							arena.getPlayers().add(player.getName());
							
							player.teleport(arena.getLobby());
							
							items.joinLobby(player);
							
							Bukkit.getServer().getPluginManager().callEvent(new PlayerJoinArenaEvent(player, arena));
							
							return;
							
						}
					}

				}

			}
			
			Arena arena = available.get(0);
			
			arena.setTime(120);
			
			arena.getPlayers().add(player.getName());
			
			player.teleport(arena.getLobby());
			/*player.teleport(arena.getTempspawn().get(0));
			
			arena.getTempspawn().remove(arena.getTempspawn().get(0));*/
			
			items.joinLobby(player);
			
			player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Match will start in " + ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + arena.getTime() + " seconds" + ChatColor.GREEN.toString() + ChatColor.BOLD  + "...");
			
			Bukkit.getServer().getPluginManager().callEvent(new PlayerJoinArenaEvent(player, arena));
			
		} else {
			player.sendMessage(ChatColor.RED + "It seems like all of our arenas are in use, please try again in a few minutes!");
		}
		
	}
	
	public void leaveArena(Player player) {
	
		Arena arena = getArenaByPlayer(player);
		
		if(arena == null) {
			return;
		}
			
		if (arena.getPlayers().contains(player.getName())) {

			arena.getPlayers().remove(player.getName());
			
			if(arena.getPlayers().size() <= 0 && arena.getTime() > 0) {
				arena.setTime(-1);
				
				arena.getSpectators().clear();
				arena.getChests().clear();
				
				if(arena.getBlocks().size() > 0) {
					for(Location loc : arena.getBlocks()) {
						loc.getBlock().setType(Material.AIR);
					}
				}
				
				arena.getBlocks().clear();
				arena.getTempspawn().clear();
				
				for(Location loc : arena.getSpawns()) {
					arena.getTempspawn().add(loc);
				}
				
				arena.getPlayers().clear();
				arena.setRefillTimer(120);
				
				if(!available.contains(arena)) {
					available.add(arena);
				}
				
				SkyWars.getInstance().teleportSpawn(player);
				
			}
			
			Bukkit.getServer().getPluginManager().callEvent(new PlayerLeaveArenaEvent(player, arena));

			SkyWars.getInstance().teleportSpawn(player);
			
			items.defaultItems(player);
		}
		
	}
	
	public void removePlayerFromArena(Player player) {
		
		Arena arena = getArenaByPlayer(player);
		
		if(arena == null) {
			return;
		}
		
			
		if (arena.getPlayers().contains(player.getName())) {

			items.defaultItems(player);

			arena.getSpectators().clear();
			arena.getChests().clear();
			
			if(arena.getBlocks().size() > 0) {
				for(Location loc : arena.getBlocks()) {
					loc.getBlock().setType(Material.AIR);
				}
			}
			
			arena.getBlocks().clear();
			arena.getTempspawn().clear();
			
			for(Location loc : arena.getSpawns()) {
				arena.getTempspawn().add(loc);
			}
			
			arena.getPlayers().clear();
			arena.setRefillTimer(120);
			
			if(!available.contains(arena)) {
				available.add(arena);
			}
			
		}
			
		
	}
	
	public ArrayList<Arena> getArenas() {
		return arenas;
	}
	
	public ArrayList<Arena> getAvailable() {
		return available;
	}
}
