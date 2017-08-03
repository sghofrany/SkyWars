package me.iran.skywars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.arena.ArenaRunnables;
import me.iran.skywars.arena.LootManager;
import me.iran.skywars.arena.cmd.ArenaCommands;
import me.iran.skywars.arena.events.ArenaCenterEvents;
import me.iran.skywars.arena.events.BlockEvents;
import me.iran.skywars.arena.events.EditEvents;
import me.iran.skywars.arena.events.PlayerJoinArena;
import me.iran.skywars.arena.events.PlayerLeaveArena;
import me.iran.skywars.duel.Spectate;
import me.iran.skywars.duel.events.ChestEvent;
import me.iran.skywars.duel.events.DisconnectInDuel;
import me.iran.skywars.duel.events.DuelDeathEvent;
import me.iran.skywars.duel.events.DuelEnd;
import me.iran.skywars.duel.events.DuelStart;
import me.iran.skywars.duel.events.PickItemUp;
import me.iran.skywars.duel.events.RespawnEvent;
import me.iran.skywars.items.HotbarItems;
import me.iran.skywars.items.PlayerInventories;
import me.iran.skywars.items.events.CancelItemDrop;
import me.iran.skywars.items.events.InteractWithItemsInHand;
import me.iran.skywars.items.events.InventoryClick;
import me.iran.skywars.items.events.OnJoinItems;
import me.iran.skywars.kits.KitManager;
import me.iran.skywars.kits.cmd.KitCommands;

public class SkyWars extends JavaPlugin implements Listener {

	private static SkyWars instance;
	
	//private InventoryRunnables invRun = new InventoryRunnables(this);
	//private Queue queue = new Queue();
	private ArenaRunnables arenaRun = new ArenaRunnables();
	private PlayerInventories inv = new PlayerInventories();
	private HotbarItems items = new HotbarItems();
	
	public static SkyWars getInstance() {
		return instance;
	}
	
	public void onEnable() {
		
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin by: Irantwomiles");
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Twitter @Irantwomiles");
		
		instance = this;
		
		ArenaManager.getManager().loadArenas();
		KitManager.getManager().loadKits();
		LootManager.getManager().loadLoot();
		
		getCommand("arena").setExecutor(new ArenaCommands(this));
		getCommand("duel").setExecutor(new ArenaCommands(this));
		getCommand("kit").setExecutor(new KitCommands());
		getCommand("spectate").setExecutor(new Spectate());
		
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new EditEvents(this), this);
		Bukkit.getPluginManager().registerEvents(new OnJoinItems(this), this);
		Bukkit.getPluginManager().registerEvents(new CancelItemDrop(this), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new InteractWithItemsInHand(), this);
		Bukkit.getPluginManager().registerEvents(new DuelDeathEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinArena(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeaveArena(), this);
		Bukkit.getPluginManager().registerEvents(new DuelStart(), this);
		Bukkit.getPluginManager().registerEvents(new DuelEnd(), this);
		Bukkit.getPluginManager().registerEvents(new ChestEvent(), this);
		Bukkit.getPluginManager().registerEvents(new RespawnEvent(), this);
		Bukkit.getPluginManager().registerEvents(new BlockEvents(), this);
		Bukkit.getPluginManager().registerEvents(new PickItemUp(), this);
		Bukkit.getPluginManager().registerEvents(new DisconnectInDuel(), this);
		Bukkit.getPluginManager().registerEvents(new Spectate(), this);
		//Bukkit.getPluginManager().registerEvents(new ArenaCenterEvents(), this);
		
		//invRun.runTaskTimer(this, 20, 20);
		//queue.runTaskTimer(this, 20, 20);
		arenaRun.runTaskTimer(this, 20, 20);
		
	}
	
	public void onDisable() {
		ArenaManager.getManager().saveArenas();
		KitManager.getManager().saveKits();
		LootManager.getManager().saveLoot();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			
			if(!player.hasPermission("skywars.staff")) {
				player.sendMessage(ChatColor.RED + "No Permission.");
				return true;
			}
			
			if(getConfig().contains("spawn")) {
				
				getConfig().set("spawn.x", player.getLocation().getX());
				getConfig().set("spawn.y", player.getLocation().getY());
				getConfig().set("spawn.z", player.getLocation().getZ());
				getConfig().set("spawn.pitch", player.getLocation().getPitch());
				getConfig().set("spawn.yaw", player.getLocation().getYaw());
				getConfig().set("spawn.world", player.getLocation().getWorld().getName());
				
				saveConfig();
			
				player.sendMessage(ChatColor.GOLD + "Spawn is now set!");
				
			} else {
				
				getConfig().createSection("spawn.x");
				getConfig().createSection("spawn.y");
				getConfig().createSection("spawn.z");
				getConfig().createSection("spawn.pitch");
				getConfig().createSection("spawn.yaw");
				getConfig().createSection("spawn.world");
				
				getConfig().set("spawn.x", player.getLocation().getX());
				getConfig().set("spawn.y", player.getLocation().getY());
				getConfig().set("spawn.z", player.getLocation().getZ());
				getConfig().set("spawn.pitch", player.getLocation().getPitch());
				getConfig().set("spawn.yaw", player.getLocation().getYaw());
				getConfig().set("spawn.world", player.getLocation().getWorld().getName());
				
				saveConfig();
			
				player.sendMessage(ChatColor.GOLD + "Spawn is now set!");
				
			}
			
		}
		
		if(cmd.getName().equalsIgnoreCase("loot")) {
			
			if(!player.hasPermission("skywars.staff")) {
				player.sendMessage(ChatColor.RED + "No Permission.");
				return true;
			}

			if(args[0].equalsIgnoreCase("settier1")) {
				LootManager.getLoot().setTier1(player.getInventory().getContents());
				player.sendMessage(ChatColor.GREEN + "Tier 1 loot set");
			}
			
			if(args[0].equalsIgnoreCase("settier2")) {
				LootManager.getLoot().setTier2(player.getInventory().getContents());
				player.sendMessage(ChatColor.GREEN + "Tier 2 loot set");
			}
			
			if(args[0].equalsIgnoreCase("show")) {
				inv.viewLoot(player);
			}
			
		}
		
		return true;
	}
	
	public void teleportSpawn(Player player) {
		
		double x = SkyWars.getInstance().getConfig().getDouble("spawn.x");
		double y = SkyWars.getInstance().getConfig().getDouble("spawn.y");
		double z = SkyWars.getInstance().getConfig().getDouble("spawn.z");
		float pitch = (float) SkyWars.getInstance().getConfig().getDouble("spawn.pitch");
		float yaw = (float) SkyWars.getInstance().getConfig().getDouble("spawn.yaw");
		String world =  SkyWars.getInstance().getConfig().getString("spawn.world");
		
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		
		loc.setPitch(pitch);
		loc.setYaw(yaw);
		
		player.teleport(loc);
		
		player.sendMessage(ChatColor.GOLD + "Teleported to Spawn!");
		
		items.defaultItems(player);
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		teleportSpawn(event.getPlayer());
		
		event.getPlayer().setGameMode(GameMode.SURVIVAL);
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!p.hasPermission("skywars.staff")) {
				p.showPlayer(event.getPlayer());
				event.getPlayer().showPlayer(p);
			}
		}
		
		Spectate.leaveSpectator(event.getPlayer());
		
	}
	
}
