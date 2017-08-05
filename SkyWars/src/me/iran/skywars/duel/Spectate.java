package me.iran.skywars.duel;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.arena.Arena;
import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.items.HotbarItems;

public class Spectate implements CommandExecutor, Listener {

	private static HotbarItems items = new HotbarItems();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!(sender instanceof Player)) {
			return true;
		}
			
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("spectate")) {
			
			if(args.length < 1) {
				player.sendMessage(ChatColor.RED + "/spectate <player>");
				return true;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null) {
				player.sendMessage(ChatColor.RED + "Could not find the player " + args[0]);
				return true;
			}
			
			if(DuelManager.getManager().isPlayerInDuel(player)) {
				player.sendMessage(ChatColor.RED + "Can't use spectator mode while in a match");
				return true;
			}
			
			if(!DuelManager.getManager().isPlayerInDuel(target)) {
				player.sendMessage(ChatColor.RED + "That person doesn't seem to be fighting anyone currently");
				return true;
			}
			
			Duel duel = DuelManager.getManager().getDuelByPlayer(target);
			
			Arena arena = duel.getArena();
			
			if(ArenaManager.getManager().isPlayerSpectating(player)) {
				
				ArenaManager.getManager().getSpectatingArena(player).getSpectators().remove(player.getName());
				
				arena.getSpectators().add(player.getName());
				
				player.teleport(target.getLocation());
				
				items.spectatorItems(player);
				
				for(String s : duel.getAlive()) {
					
					Player p = Bukkit.getPlayer(s);
					
					if(p.canSee(player)) {
						p.hidePlayer(player);
						player.showPlayer(p);
					}
					
				}
				
				for(String s : arena.getSpectators()) {
					
					Player p = Bukkit.getPlayer(s);
					
					p.showPlayer(player);
					player.showPlayer(p);
					
				}
				
				player.setGameMode(GameMode.CREATIVE);
				
				player.sendMessage(ChatColor.YELLOW + "You are now spectating " + ChatColor.AQUA + target.getName());
				
			} else {
				
				arena.getSpectators().add(player.getName());
				
				player.teleport(target.getLocation());
				
				items.spectatorItems(player);
				
				for(String s : duel.getAlive()) {
					
					Player p = Bukkit.getPlayer(s);
					
					if(p.canSee(player)) {
						p.hidePlayer(player);
						player.showPlayer(p);
					}
					
				}
				
				for(String s : arena.getSpectators()) {
					
					Player p = Bukkit.getPlayer(s);
					
					p.showPlayer(player);
					player.showPlayer(p);
					
				}
				
				player.setGameMode(GameMode.CREATIVE);
				
				player.sendMessage(ChatColor.YELLOW + "You are now spectating " + ChatColor.AQUA + target.getName());
			}
			
		}
		
		return true;
	}
	
	public static void makeSpectator(Player player, String t) {
		
		Player target = Bukkit.getPlayer(t);
		
		if(target == null) {
			player.sendMessage(ChatColor.RED + "Could not find the player " + t);
			return;
		}
		
		if(DuelManager.getManager().isPlayerInDuel(player)) {
			player.sendMessage(ChatColor.RED + "Can't use spectator mode while in a match");
			return;
		}
		
		if(!DuelManager.getManager().isPlayerInDuel(target)) {
			player.sendMessage(ChatColor.RED + "That person doesn't seem to be fighting anyone currently");
			return;
		}
		
		Arena arena = DuelManager.getManager().getDuelByPlayer(target).getArena();
		
		if(ArenaManager.getManager().isPlayerSpectating(player)) {
			
			ArenaManager.getManager().getSpectatingArena(player).getSpectators().remove(player.getName());
			
			arena.getSpectators().add(player.getName());
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), new Runnable() {
				
				public void run() {
					items.spectatorItems(player);
					player.teleport(target.getLocation());
				}
				
			}, 10);
			
			Duel duel = DuelManager.getManager().getDuelByPlayer(target);
			
			for(String s : duel.getAlive()) {
				
				Player p = Bukkit.getPlayer(s);
				
				if(p.canSee(player)) {
					p.hidePlayer(player);
					player.showPlayer(p);
				}
				
			}
			
			for(String s : arena.getSpectators()) {
				
				Player p = Bukkit.getPlayer(s);
				
				p.showPlayer(player);
				player.showPlayer(p);
				
			}
			
			player.setGameMode(GameMode.CREATIVE);
			
			player.sendMessage(ChatColor.YELLOW + "You are now spectating " + ChatColor.AQUA + target.getName());
			
		} else {
			
			arena.getSpectators().add(player.getName());
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(SkyWars.getInstance(), new Runnable() {
				
				public void run() {
					items.spectatorItems(player);
					player.teleport(target.getLocation());
				}
				
			}, 10);
			
			Duel duel = DuelManager.getManager().getDuelByPlayer(target);
			
			for(String s : duel.getAlive()) {
				
				Player p = Bukkit.getPlayer(s);
				
				if(p.canSee(player)) {
					p.hidePlayer(player);
					player.showPlayer(p);
				}
				
			}
			
			for(String s : arena.getSpectators()) {
				
				Player p = Bukkit.getPlayer(s);
				
				p.showPlayer(player);
				player.showPlayer(p);
				
			}
			
			player.setGameMode(GameMode.CREATIVE);
			
			player.sendMessage(ChatColor.YELLOW + "You are now spectating " + ChatColor.AQUA + target.getName());
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void leaveSpectator(Player player) {
		
		if(!ArenaManager.getManager().isPlayerSpectating(player)) {
			player.sendMessage(ChatColor.RED + "You are currently not spectating");
			return;
		}
		
		Arena arena = ArenaManager.getManager().getSpectatingArena(player);
		
		arena.getSpectators().remove(player.getName());
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.showPlayer(player);
		}

		player.setGameMode(GameMode.SURVIVAL);
		
		items.defaultItems(player);
		
		SkyWars.getInstance().teleportSpawn(player);
		
		player.sendMessage(ChatColor.RED + "You have left spectator mode");
		
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		
		Player player = event.getPlayer();
		
		if(ArenaManager.getManager().isPlayerSpectating(player)) {
			event.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			
			Player player = (Player) event.getDamager();
			
			if(ArenaManager.getManager().isPlayerSpectating(player)) {
				event.setCancelled(true);
			}
			
			if(!DuelManager.getManager().isPlayerInDuel(player)) {
				event.setCancelled(true);
			}
			
		}
	}
	
	@EventHandler
	public void onSplash(PotionSplashEvent event) {

		Iterator<LivingEntity> iter = event.getAffectedEntities().iterator();

		while (iter.hasNext()) {
		    Entity entity = iter.next();

		    if(entity instanceof Player) {
				
				Player player = (Player) entity;
				
				if(ArenaManager.getManager().isPlayerSpectating(player)) {
					event.getAffectedEntities().remove(entity);
				}
			}
		}
		
	}
	
}
