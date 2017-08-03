package me.iran.skywars.duel.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.iran.skywars.arena.Arena;
import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;

public class DisconnectInDuel implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
	
		event.setQuitMessage(null);
		
		Player player = event.getPlayer();
		
		if(!DuelManager.getManager().isPlayerInDuel(player) && ArenaManager.getManager().isPlayerInArena(player)) {
			
			ArenaManager.getManager().leaveArena(player);
			
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
		
		if(duel.getAlive().contains(player.getName())) {
			duel.getAlive().remove(player.getName());
			
			if(duel.getAlive().size() == 1) {
				DuelManager.getManager().endUnrankedSolo(Bukkit.getPlayer(duel.getAlive().get(0)));
			}
			
		}
		
		if(arena.getPlayers().contains(player.getName())) {
			arena.getPlayers().remove(player.getName());
		}
		
		if(arena.getSpectators().contains(player.getName())) {
			arena.getSpectators().remove(player.getName());
		}
		
	}
	
}
