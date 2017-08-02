package me.iran.skywars.duel.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.iran.skywars.customevents.DuelStartEvent;
import me.iran.skywars.items.HotbarItems;

public class DuelStart implements Listener {

	private HotbarItems item = new HotbarItems();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void duelStart(DuelStartEvent event) {
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			if(event.getPlayers().contains(p.getName())) {
				
				p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Match started with " + event.getPlayers().size() + " players!");
				
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				
				item.joinLobby(p);
				
			}
		}
		
	}
	
}
