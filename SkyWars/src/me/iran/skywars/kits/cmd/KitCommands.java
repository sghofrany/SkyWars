package me.iran.skywars.kits.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.iran.skywars.kits.Kit;
import me.iran.skywars.kits.KitManager;

import org.bukkit.entity.Player;

public class KitCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("kit")) {
			
			if(args.length < 1) {
				player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
				player.sendMessage(ChatColor.GOLD + "* Kit Commands *");
				player.sendMessage(ChatColor.GRAY + "- /kit create <name>"  + ChatColor.YELLOW + " [Create a new Kit]");
				player.sendMessage(ChatColor.GRAY + "- /kit delete <name>"  + ChatColor.YELLOW + " [Delete a Kit]");
				player.sendMessage(ChatColor.GRAY + "- /kit info <name>"  + ChatColor.YELLOW + " [Get info about a Kit]");
				player.sendMessage(ChatColor.GRAY + "- /kit set <name>"  + ChatColor.YELLOW + " [Reset the kit items with your current items]");
				player.sendMessage(ChatColor.GRAY + "- /kit setdisplay <name>"  + ChatColor.YELLOW + " [Set the kit display to the item in your hand]");
				player.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
				return true;
			}
			
			if(args[0].equalsIgnoreCase("create")) {
				
				if(args.length < 2) {
					player.sendMessage(ChatColor.RED + "/kit create <name>");
					return true;
				}
				
				KitManager.getManager().createKit(player, args[1]);
				
			}
			
			if(args[0].equalsIgnoreCase("delete")) {
				
				if(args.length < 2) {
					player.sendMessage(ChatColor.RED + "/kit delete <name>");
					return true;
				}
				
				KitManager.getManager().deleteKit(player, args[1]);
				
			}
			
			if(args[0].equalsIgnoreCase("info")) {
				
				if(args.length < 2) {
					player.sendMessage(ChatColor.RED + "/kit info <name>");
					return true;
				}
				
				player.sendMessage(ChatColor.RED + "Coming soon...");
				
			}
			
			if(args[0].equalsIgnoreCase("set")) {
				
				if(args.length < 2) {
					player.sendMessage(ChatColor.RED + "/kit set <name>");
					return true;
				}
				
				Kit kit = KitManager.getManager().getKitByName(args[1]);
				
				if(kit == null) {
					player.sendMessage(ChatColor.RED + "Couldn't find a kit with the name of " + args[1]);
					return true;
				}
				
				kit.setInv(player.getInventory().getContents());
				kit.setArmor(player.getInventory().getArmorContents());
				
				player.sendMessage(ChatColor.YELLOW.toString() + "Edited kit " + ChatColor.DARK_PURPLE + kit.getName());
			}
			
			
			if(args[0].equalsIgnoreCase("setdisplay")) {
				
				if(args.length < 2) {
					player.sendMessage(ChatColor.RED + "/kit setdisplay <name>");
					return true;
				}
				
				Kit kit = KitManager.getManager().getKitByName(args[1]);
				
				if(kit == null) {
					player.sendMessage(ChatColor.RED + "Couldn't find a kit with the name of " + args[1]);
					return true;
				}
				
				if(player.getItemInHand().getType() != null) {
					kit.setDisplay(player.getItemInHand());
					
					player.sendMessage(ChatColor.YELLOW.toString() + "Set display item for kit " + ChatColor.DARK_PURPLE + kit.getName());
					
				} else {
					player.sendMessage(ChatColor.RED + "You must have something in your hand");
				}
			
			}
		}
		
		return true;
	}
	
}
