package me.iran.skywars.gametype;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GameTypeManager {

	private static ArrayList<GameType> games = new ArrayList<>();
	
	public GameTypeManager () {}
	
	private static GameTypeManager gm;
	
	public static GameTypeManager getManager() {
		
		if(gm == null) {
			gm = new GameTypeManager();
		}
		
		return gm;
		
	}
	
	public void createGameType(Player player, String name) {
		
		if(getGameByName(name) != null) {
			player.sendMessage(ChatColor.RED + "Gametype with that name already exists");
			return;
		}
		
		GameType game = new  GameType(name);
		
		games.add(game);
		
	}
	
	public GameType getGameByName(String name) {
		for(GameType game : games) {
			if(game.getName().equalsIgnoreCase(name)) {
				return game;
			}
		}
		
		return null;
	}
	
}
