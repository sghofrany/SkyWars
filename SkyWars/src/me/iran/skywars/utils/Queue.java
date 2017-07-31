package me.iran.skywars.utils;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.duel.DuelManager;

public class Queue extends BukkitRunnable {

	private static ArrayList<String> unrankedSolo = new ArrayList<>();
	
	public static void soloUnrankedQueue() {
		
		if(unrankedSolo.size() >= 8) {
			
			if(ArenaManager.getManager().getAvailable().size() > 0) {
				
				ArrayList<String> players = new ArrayList<>();
				
				for(int i = 0; i < 8; i++) {
					players.add(unrankedSolo.get(i));
				}
				
				for(int i = 0; i < 8; i++) {
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
