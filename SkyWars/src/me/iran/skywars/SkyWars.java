package me.iran.skywars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.arena.ArenaRunnables;
import me.iran.skywars.arena.cmd.ArenaCommands;
import me.iran.skywars.arena.events.EditEvents;
import me.iran.skywars.arena.events.PlayerJoinArena;
import me.iran.skywars.arena.events.PlayerLeaveArena;
import me.iran.skywars.duel.events.DuelDeathEvent;
import me.iran.skywars.duel.events.DuelEnd;
import me.iran.skywars.duel.events.DuelStart;
import me.iran.skywars.items.InventoryRunnables;
import me.iran.skywars.items.events.CancelBlockPlace;
import me.iran.skywars.items.events.CancelItemDrop;
import me.iran.skywars.items.events.InteractWithItemsInHand;
import me.iran.skywars.items.events.InventoryClick;
import me.iran.skywars.items.events.OnJoinItems;
import me.iran.skywars.kits.KitManager;
import me.iran.skywars.kits.cmd.KitCommands;
import me.iran.skywars.utils.Queue;

public class SkyWars extends JavaPlugin {

	private static SkyWars instance;
	
	private InventoryRunnables invRun = new InventoryRunnables(this);
	private Queue queue = new Queue();
	private ArenaRunnables arenaRun = new ArenaRunnables();
	
	public static SkyWars getInstance() {
		return instance;
	}
	
	public void onEnable() {
		
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin by: Irantwomiles");
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Twitter @Irantwomiles");
		
		instance = this;
		
		ArenaManager.getManager().loadArenas();
		KitManager.getManager().loadKits();
		
		getCommand("arena").setExecutor(new ArenaCommands(this));
		getCommand("duel").setExecutor(new ArenaCommands(this));
		getCommand("kit").setExecutor(new KitCommands());
		
		Bukkit.getPluginManager().registerEvents(new EditEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new OnJoinItems(this), this);
		Bukkit.getPluginManager().registerEvents(new CancelItemDrop(this), this);
		Bukkit.getPluginManager().registerEvents(new CancelBlockPlace(this), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new InteractWithItemsInHand(), this);
		Bukkit.getPluginManager().registerEvents(new DuelDeathEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinArena(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeaveArena(), this);
		Bukkit.getPluginManager().registerEvents(new DuelStart(), this);
		Bukkit.getPluginManager().registerEvents(new DuelEnd(), this);
		
		//invRun.runTaskTimer(this, 20, 20);
		//queue.runTaskTimer(this, 20, 20);
		arenaRun.runTaskTimer(this, 20, 20);
		
	}
	
	public void onDisable() {
		ArenaManager.getManager().saveArenas();
		KitManager.getManager().saveKits();
	}
	
}
