package me.iran.skywars.duel.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

import me.iran.skywars.customevents.DuelStartEvent;
import me.iran.skywars.items.HotbarItems;
import me.iran.skywars.items.events.InventoryClick;
import me.iran.skywars.kits.Kit;
import me.iran.skywars.utils.Scoreboards;

public class DuelStart implements Listener {

	private HotbarItems item = new HotbarItems();
	
	private InventoryClick kit = new InventoryClick();
	
	private Scoreboards sb = new Scoreboards();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void duelStart(DuelStartEvent event) {
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			if(event.getPlayers().contains(p.getName())) {
				
				p.setScoreboard(sb.inGameSolo(p));
				
				p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Match started with " + event.getPlayers().size() + " players!");
				
				p.getInventory().clear();
				p.getInventory().setArmorContents(null);
				
				if(kit.getChosenKit().containsKey(p.getName())) {
					
					Kit k = kit.getChosenKit().get(p.getName());
					
					p.getInventory().setContents(k.getInv());
					p.getInventory().setArmorContents(k.getArmor());
					
				} else {
					item.kit(p);
				}
				
			}
		}
		
	}
	
}
