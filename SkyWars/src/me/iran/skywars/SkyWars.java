package me.iran.skywars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.arena.cmd.ArenaCommands;
import me.iran.skywars.arena.events.EditEvents;
import me.iran.skywars.items.events.CancelDrop;
import me.iran.skywars.items.events.CancelInventoryClick;
import me.iran.skywars.items.events.OnJoinItems;

public class SkyWars extends JavaPlugin {

	private static SkyWars instance;
	
	public static SkyWars getInstance() {
		return instance;
	}
	
	public void onEnable() {
		
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin by: Irantwomiles");
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Twitter @Irantwomiles");
		
		instance = this;
		
		ArenaManager.getManager().loadArenas();
		
		getCommand("arena").setExecutor(new ArenaCommands(this));
		getCommand("duel").setExecutor(new ArenaCommands(this));
		
		Bukkit.getPluginManager().registerEvents(new EditEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new OnJoinItems(this), this);
		Bukkit.getPluginManager().registerEvents(new CancelDrop(this), this);
		Bukkit.getPluginManager().registerEvents(new CancelInventoryClick(this), this);
	}
	
	public void onDisable() {
		ArenaManager.getManager().saveArenas();
	}
	
}
