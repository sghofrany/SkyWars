package me.iran.skywars.duel;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.iran.skywars.SkyWars;
import me.iran.skywars.arena.Arena;
import me.iran.skywars.arena.ArenaManager;
import net.md_5.bungee.api.ChatColor;

public class DuelManager {

	private static ArrayList<Duel> duels = new ArrayList<>();
	
	private static DuelManager manager;
	
	public static DuelManager getManager() {
		
		if(manager == null) {
			manager = new DuelManager();
		}
		
		return manager;
	}
	
	@SuppressWarnings("deprecation")
	public void unrankedSolo(Arena arena, ArrayList<String> players) {
		
		Duel duel = new Duel(arena);
		
		duel.setPlayers(players);
		
		for(String s : players) {
			
			duel.getAlive().add(s);
		}
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			if(duel.getPlayers().contains(p.getName())) {
				
				p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Match started with " + duel.getPlayers().size() + " players!");
				
			}
		}
		
		duels.add(duel);
		
	}
	
	@SuppressWarnings("deprecation")
	public void endUnrankedSolo(Player player) {
		
		Duel duel = getDuelByPlayer(player);
		
		if(duel == null) {
			return;
		}
		
		if(duel.getAlive().size() == 1) {
			
			for(String s : duel.getPlayers()) {
				System.out.println(s);
			}
			
			duel.setWinner(duel.getAlive().get(0));
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(duel.getPlayers().contains(p.getName())) {
					p.sendMessage("");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "WINNER: " + ChatColor.YELLOW + duel.getWinner());
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "Other Information goes here");
				}
			}

			Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), new Runnable() {
				public void run() {
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(duel.getPlayers().contains(p.getName())) {
							p.sendMessage(ChatColor.GREEN + "Removed from Arena");
						}
					}
				}
			}, 60);
			
			duels.remove(duel);
			
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

	public Duel getDuelByPlayer(Player player) {
		
		for(Duel duel : duels) {
			if(duel.getPlayers().contains(player.getName())) {
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
