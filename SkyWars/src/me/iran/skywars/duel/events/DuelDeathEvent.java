package me.iran.skywars.duel.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;
import me.iran.skywars.duel.Spectate;
import net.md_5.bungee.api.ChatColor;

public class DuelDeathEvent implements Listener {

	SkyWars plugin;
	
	public DuelDeathEvent (SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
	
		event.setDeathMessage(null);
		
		Player player = event.getEntity();
		
		if(!DuelManager.getManager().isPlayerInDuel(player)) {
			event.getDrops().clear();
		}
		
		if(DuelManager.getManager().isPlayerInDuel(player)) {
			
			Duel duel = DuelManager.getManager().getDuelByPlayer(player);
			
			if(duel.getArena().getPlayers().contains(player.getName())) {
				duel.getArena().getPlayers().remove(player.getName());
			}
			
			if(event.getEntity().getKiller() instanceof Player) {
				
				if(duel.getAlive().contains(player.getName())) {
					
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						if(duel.getArena().getPlayers().contains(p.getName()) || duel.getArena().getSpectators().contains(p.getName())) {
							
							p.sendMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " has been wanked by " + ChatColor.DARK_PURPLE + event.getEntity().getKiller().getName());
							
						}
					}
					
				}
			} else {
				
				if(duel.getAlive().contains(player.getName())) {
					
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						if(duel.getArena().getPlayers().contains(p.getName()) || duel.getArena().getSpectators().contains(p.getName())) {
							
							p.sendMessage(ChatColor.RED + player.getName() + ChatColor.YELLOW + " has been wanked by something unknown");
							
						}
					}
					
				}
			}
			
			if(duel.getAlive().contains(player.getName())) {
				
				duel.getAlive().remove(player.getName());

				if(duel.getAlive().size() == 1) {
					duel.getArena().getSpectators().add(player.getName());
					
					Player p = Bukkit.getPlayer(duel.getAlive().get(0));
					
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);

					SkyWars.getInstance().teleportSpawn(p);
					
					DuelManager.getManager().endUnrankedSolo(Bukkit.getPlayer(duel.getAlive().get(0)));
					
				} else {
					Spectate.makeSpectator(player, duel.getAlive().get(0));
				}
			
			}
			
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), new Runnable() {
			public void run() {
				player.spigot().respawn();
				SkyWars.getInstance().teleportSpawn(player);
			}
		}, 5);
		
	}
	
}
