package me.iran.skywars.utils;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.duel.DuelManager;
import net.md_5.bungee.api.ChatColor;

public class Queue extends BukkitRunnable {

	private static ArrayList<String> unrankedSolo = new ArrayList<>();
	
	public static void soloUnrankedQueue() {
		
		if(unrankedSolo.size() >= 2) {
			
			if(ArenaManager.getManager().getAvailable().size() > 0) {
				
				ArrayList<String> players = new ArrayList<>();
				
				for(int i = 0; i < 2; i++) {
					players.add(unrankedSolo.get(i));
				}
				
				for(int i = 0; i < 2; i++) {
					unrankedSolo.remove(i);
				}
				
				DuelManager.getManager().unrankedSolo(ArenaManager.getManager().getAvailable().get(0), players);
				
			}
			
		}
		
	}
	
	public void run() {
		soloUnrankedQueue();
	}
	
	public ArrayList<String> getUnrankedSoloQueue() {
		return unrankedSolo;
	}
	
	public void queueSoloUnranked(Player player) {
		if(!unrankedSolo.contains(player.getName())) {
			unrankedSolo.add(player.getName());
			player.sendMessage(ChatColor.GREEN + "You have joined Solo Unranked Queue!");
		} else {
			unrankedSolo.remove(player.getName());
			player.sendMessage(ChatColor.RED + "You have left Solo Unranked Queue!");
		}
	}
	
	public void joinSoloUnrankedQueue(Player player) {
		if(!unrankedSolo.contains(player.getName())) {
			unrankedSolo.add(player.getName());
		}
	}
	
	public void leaveSoloUnrankedQueue(Player player) {
		if(unrankedSolo.contains(player.getName())) {
			unrankedSolo.remove(player.getName());
		}
	}
	
	
}
