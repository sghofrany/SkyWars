package me.iran.skywars.duel;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.iran.skywars.SkyWars;
import me.iran.skywars.arena.Arena;
import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.customevents.DuelEndEvent;
import me.iran.skywars.customevents.DuelStartEvent;
import me.iran.skywars.items.HotbarItems;

public class DuelManager {

	private HotbarItems items = new HotbarItems();
	
	private static ArrayList<Duel> duels = new ArrayList<>();
	
	private static DuelManager manager;
	
	public static DuelManager getManager() {
		
		if(manager == null) {
			manager = new DuelManager();
		}
		
		return manager;
	}
	
	public void unrankedSolo(Arena arena, ArrayList<String> players) {
		
		Duel duel = new Duel(arena);
		
		for(String s : players) {
			
			duel.getAlive().add(s);
		}
		
		if(duel.getAlive().size() <= duel.getArena().getTempspawn().size()) {
			
			for(int i = 0; i < duel.getAlive().size(); i++) {
				
				Player player = Bukkit.getPlayer(duel.getAlive().get(i));
				
				player.teleport(duel.getArena().getTempspawn().get(i));
				
			}
		}
		
		ArenaManager.getManager().getAvailable().remove(arena);
		
		duels.add(duel);
		
		Bukkit.getServer().getPluginManager().callEvent(new DuelStartEvent(players, duel, duel.getArena()));
	}
	
	@SuppressWarnings("deprecation")
	public void endUnrankedSolo(Player player) {
		
		Duel duel = getDuelByPlayer(player);
		
		if(duel == null) {
			return;
		}
		
		if(duel.getAlive().size() == 1) {
			
			duel.setWinner(duel.getAlive().get(0));
			
			Bukkit.getServer().getPluginManager().callEvent(new DuelEndEvent(duel.getAlive(), duel, duel.getArena()));
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), new Runnable() {
				
				public void run() {

					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						
						if(duel.getAlive().contains(p.getName()) || duel.getArena().getSpectators().contains(p.getName())) {
							
							SkyWars.getInstance().teleportSpawn(p);
							
							items.defaultItems(p);
							
							ArenaManager.getManager().removePlayerFromArena(p);
							
							duels.remove(duel);
							
						}
					}
				}
			}, 60);
			
		}
		
	}
	
	public Duel getDuelByArena(Arena arena) {
		
		for(Duel duel : duels) {
			if(duel.getArena().getId().equalsIgnoreCase(arena.getId())) {
				return duel;
			}
		}
		
		return null;
		
	}

	public boolean isArenaInUse(Arena arena) {
		
		for(Duel duel : duels) {
			if(duel.getArena().getId().equalsIgnoreCase(arena.getId())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public Duel getDuelByPlayer(Player player) {
		
		for(Duel duel : duels) {
			if(duel.getAlive().contains(player.getName())) {
				return duel;
			}
		}
		
		return null;
		
	}
	
	public boolean isPlayerInDuel(Player player) {
		for(Duel duel : duels) {
			if(duel.getAlive().contains(player.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Duel> getDules() {
		return duels;
	}
	
}
