package me.iran.skywars.arena.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.iran.skywars.arena.Arena;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;
import net.md_5.bungee.api.ChatColor;

public class BlockEvents implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		
		Player player = event.getPlayer();
		
		if(!DuelManager.getManager().isPlayerInDuel(player) && !player.hasPermission("skywars.staff")) {
			event.setCancelled(true);
			return;
		}
		
		Duel duel = DuelManager.getManager().getDuelByPlayer(player);
		
		if(duel == null) {
			return;
		}
		
		Arena arena = DuelManager.getManager().getDuelByPlayer(player).getArena();
		
		if(arena == null) {
			return;
		}
		
		if(duel.getAlive().contains(player.getName())) {
			
			arena.getBlocks().add(event.getBlock().getLocation());
			
		}
		
	}
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		
		if(!DuelManager.getManager().isPlayerInDuel(player) && !player.hasPermission("skywars.staff")) {
			event.setCancelled(true);
			return;
		}
		
		Duel duel = DuelManager.getManager().getDuelByPlayer(player);
		
		if(duel == null) {
			return;
		}
		
		Arena arena = DuelManager.getManager().getDuelByPlayer(player).getArena();
		
		if(arena == null) {
			return;
		}
		
		if(duel.getAlive().contains(player.getName())) {
			
			if(!arena.getBlocks().contains(event.getBlock().getLocation())) {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You can only break blocks that were placed by you or other players!");
			}
			
		}
		
	}
}
