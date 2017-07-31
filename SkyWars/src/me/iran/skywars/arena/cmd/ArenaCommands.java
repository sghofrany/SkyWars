package me.iran.skywars.arena.cmd;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.iran.skywars.SkyWars;
import me.iran.skywars.arena.Arena;
import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.duel.DuelManager;

public class ArenaCommands implements CommandExecutor {

	SkyWars plugin;
	
	public ArenaCommands (SkyWars plugin) {
		this.plugin = plugin;
	}
	
	private static HashMap<String, Arena> edit = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("arena")) {
			
			if(!player.hasPermission("skywars.staff")) {
				player.sendMessage(ChatColor.RED + "No Permission.");
				return true;
			}
			
			if(args.length < 1) {
				player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
				player.sendMessage(ChatColor.GOLD + "* Arena Commands *");
				player.sendMessage(ChatColor.GRAY + "- /arena create <name>"  + ChatColor.YELLOW + " [Create an arena]");
				player.sendMessage(ChatColor.GRAY + "- /arena delete <id>"  + ChatColor.YELLOW + " [Delete an arena]");
				player.sendMessage(ChatColor.GRAY + "- /arena info <id>"  + ChatColor.YELLOW + " [Get info about an arena]");
				player.sendMessage(ChatColor.GRAY + "- /arena edit <id>"  + ChatColor.YELLOW + " [Start editing a arena]");
				player.sendMessage(ChatColor.GRAY + "- /arena edit leave" + ChatColor.YELLOW + " [Leave editing mode]");
				player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("create")) {
				
				if(args.length < 2) {
					player.sendMessage(ChatColor.RED + "/arena create <NAME>");
					return true;
				}
				
				ArenaManager.getManager().createArena(player, args[1]);
				
			}
			
			if(args[0].equalsIgnoreCase("edit")) {
				
				if(args.length < 2) {
					player.sendMessage(ChatColor.RED + "/arena edit <ID>");
					return true;
				}
				
				try {
				
					if(args[1].equalsIgnoreCase("leave")) {
						if(edit.containsKey(player.getName())) {
							player.sendMessage(ChatColor.RED + "You have left editing mode for arena " + ChatColor.DARK_RED + edit.get(player.getName()).getName() + " ID " + ChatColor.DARK_RED + edit.get(player.getName()).getId());
							
							edit.remove(player.getName());
						} else {
							player.sendMessage(ChatColor.RED + "You are not editing any arena at this moment");
						}
						
						return true;
					}
					
					
					Arena arena = ArenaManager.getManager().getArenaByName(args[1]);
					
					if(arena != null) {

						if(edit.containsKey(player.getName())) {
							player.sendMessage(ChatColor.RED + "You have left editing mode for arena " + edit.get(player.getName()).getName() + " ID " + edit.get(player.getName()).getId());
							
							edit.remove(player.getName());
							
							edit.put(player.getName(), arena);
							player.sendMessage(ChatColor.GREEN + "You have started editing arena " + ChatColor.DARK_GREEN + arena.getName() + " ID " + ChatColor.DARK_GREEN + arena.getId());
						} else {
							edit.put(player.getName(), arena);
							player.sendMessage(ChatColor.GREEN + "You have started editing arena " + ChatColor.DARK_GREEN + arena.getName() + " ID " + ChatColor.DARK_GREEN + arena.getId());
						}
						
					} else {
						player.sendMessage(ChatColor.RED + "Arena with the ID " + args[1] + " doesn't exist");
						
					}
					
				} catch (Exception e) {
					player.sendMessage(ChatColor.RED + "/arena edit <ID>");
				}
				
			}
			
			if(args[0].equalsIgnoreCase("info")) {
				
				if(args.length < 2) {
					player.sendMessage(ChatColor.RED + "/arena info <ID>");
					return true;
				}
				
				Arena arena = ArenaManager.getManager().getArenaByName(args[1]);
				
				if(arena == null) {
					player.sendMessage(ChatColor.RED + "Couldn't find that arena");
					return true;
				}
				
				player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
				player.sendMessage(ChatColor.GOLD + "Name: " + ChatColor.YELLOW + arena.getName());
				player.sendMessage(ChatColor.GOLD + "ID: " + ChatColor.YELLOW + arena.getId());
				player.sendMessage(ChatColor.GOLD + "Refill Time: " + ChatColor.YELLOW + arena.getRefillTimer());
				player.sendMessage(ChatColor.GOLD + "Spawn(s): " + ChatColor.YELLOW + arena.getSpawns().size());
				player.sendMessage(ChatColor.GOLD + "Team: " + ChatColor.YELLOW + arena.isTeam());
				player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
				
			}
			
		}
		
		if(cmd.getName().equalsIgnoreCase("duel")) {

			ArrayList<String> p = new ArrayList<>();
			
			p.add(player.getName());
			
			DuelManager.getManager().unrankedSolo(ArenaManager.getManager().getArenaByName(args[0]), p);
		
		}

		
		return true;
	}

	public static HashMap<String, Arena> getEdit() {
		return edit;
	}
	
}
