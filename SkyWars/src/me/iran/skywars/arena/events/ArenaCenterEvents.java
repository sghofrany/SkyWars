package me.iran.skywars.arena.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import me.iran.skywars.arena.Arena;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;

public class ArenaCenterEvents implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		
		Player player = event.getPlayer();
		
		if(!DuelManager.getManager().isPlayerInDuel(player)) {
			return;
		}
		
		Duel duel = DuelManager.getManager().getDuelByPlayer(player);
		
		if(duel == null) {
			return;
		}
		
		Arena arena = duel.getArena();
		
		if(arena == null) {
			return;
		}
		
		if (arena.getPlayers().contains(player.getName()) || arena.getSpectators().contains(player.getName())) {

			Location loc = player.getLocation();

			if (arena.getCenter().distance(loc) >= 70 && loc.getBlockY() > 20) {
				player.teleport(arena.getCenter());
				player.sendMessage(ChatColor.RED + "Reached arena border, teleported to the center");
			}

		}

	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		
		if(!DuelManager.getManager().isPlayerInDuel(player)) {
			return;
		}
		
		Duel duel = DuelManager.getManager().getDuelByPlayer(player);
		
		if(duel == null) {
			return;
		}
		
		Arena arena = duel.getArena();
		
		if(arena == null) {
			return;
		}
		
		
		if (arena.getPlayers().contains(player.getName())) {

			Location loc = event.getBlock().getLocation();

			System.out.println(arena.getCenter().distance(loc));
			
			if (arena.getCenter().distance(loc) >= 70) {

				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "Can't build past here");

			}
		}
	}
	
}
