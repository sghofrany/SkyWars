package me.iran.skywars.items.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.iran.skywars.SkyWars;
import me.iran.skywars.items.HotbarItems;

public class OnJoinItems implements Listener {

	SkyWars plugin;
	
	HotbarItems items = new HotbarItems();
	
	public OnJoinItems(SkyWars plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		items.defaultItems(event.getPlayer());
	}
	
}
