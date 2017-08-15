package me.iran.skywars.duel.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

import me.iran.skywars.customevents.DuelEndEvent;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.Spectate;
import me.iran.skywars.utils.Scoreboards;
import net.md_5.bungee.api.ChatColor;

public class DuelEnd implements Listener {

	private Scoreboards sb = new Scoreboards();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEnd(DuelEndEvent event) {
		
		Duel duel = event.getDuel();
		
		String msg = "";
		
		if(duel.getArena().getSpectators().size() > 0) {

			for (String s : duel.getArena().getSpectators()) {
				msg = msg + s + ", ";
			}

			msg = msg.substring(0, msg.length() - 2);
			
		} else {
			msg = ChatColor.RED + "None";
		}

	
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(event.getPlayers().contains(p.getName())) {
				sb.spawn(p);
				p.sendMessage("");
				p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "WINNER: " + ChatColor.YELLOW + duel.getWinner());
				p.sendMessage("");
				
				p.sendMessage(ChatColor.YELLOW + "Spectators: " + ChatColor.AQUA + msg);
				
				p.setHealth(20.0);
				p.setFoodLevel(20);
				
				for(PotionEffect effect : p.getActivePotionEffects()) {
					p.removePotionEffect(effect.getType());
				}
				
			}
			
			if(duel.getArena().getSpectators().contains(p.getName())) {
				
				Spectate.leaveSpectator(p);
				
				p.sendMessage("");
				p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "WINNER: " + ChatColor.YELLOW + duel.getWinner());
				p.sendMessage("");
				
				p.sendMessage(ChatColor.YELLOW + "Spectators: " + ChatColor.AQUA + msg);
				
				p.setHealth(20.0);
				p.setFoodLevel(20);
				
				for(PotionEffect effect : p.getActivePotionEffects()) {
					p.removePotionEffect(effect.getType());
				}
				
			}
			
		}
		
	}
	
}
