package me.iran.skywars.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.iran.skywars.SkyWars;
import me.iran.skywars.arena.Arena;
import me.iran.skywars.arena.ArenaManager;
import me.iran.skywars.duel.Duel;
import me.iran.skywars.duel.DuelManager;

public class Scoreboards extends BukkitRunnable {

	public Scoreboard waitingSolo(Player player) {
		
		if(!ArenaManager.getManager().isPlayerInArena(player)) {
			return null;
		}

		Arena arena = ArenaManager.getManager().getArenaByPlayer(player);
		
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective obj = board.registerNewObjective("sololobby", "dummy");
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo-lobby.title")));

		Score blank1 = obj.getScore(ChatColor.BOLD.toString());
		blank1.setScore(7);
		
		Team players = board.registerNewTeam("players");
		players.addEntry(ChatColor.RED.toString());
		players.setPrefix(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo-lobby.players")));
		players.setSuffix(ChatColor.WHITE + " " + arena.getPlayers().size());
		obj.getScore(ChatColor.RED.toString()).setScore(6);
		
		Score blank2 = obj.getScore(ChatColor.UNDERLINE.toString());
		blank2.setScore(5);
		
		Team start = board.registerNewTeam("start");
		start.addEntry(ChatColor.BLUE.toString());
		start.setPrefix(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo-lobby.start")));
		start.setSuffix(ChatColor.WHITE.toString() + " " + toMMSS(arena.getTime()));
		obj.getScore(ChatColor.BLUE.toString()).setScore(4);
		
		Score blank3 = obj.getScore(ChatColor.STRIKETHROUGH.toString());
		blank3.setScore(3);
		
		Team map = board.registerNewTeam("map");
		map.addEntry(ChatColor.YELLOW.toString());
		map.setPrefix(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo-lobby.map")));
		map.setSuffix(ChatColor.WHITE + " " + arena.getName());
		obj.getScore(ChatColor.YELLOW.toString()).setScore(2);
		
		Score blank4 = obj.getScore(ChatColor.ITALIC.toString());
		blank4.setScore(1);
		
		Score website = obj.getScore(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo-lobby.website")));
		website.setScore(0);
		
		return board;
	}
	
	public Scoreboard inGameSolo(Player player) {
		
		if(!DuelManager.getManager().isPlayerInDuel(player)) {
			return null;
		}
		
		Duel duel = DuelManager.getManager().getDuelByPlayer(player);
		
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective obj = board.registerNewObjective("soloingame", "dummy");
		
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo.title")));

		Score blank1 = obj.getScore(ChatColor.BOLD.toString());
		blank1.setScore(9);
		
		Team gametime = board.registerNewTeam("gametime");
		gametime.addEntry(ChatColor.RED.toString());
		gametime.setPrefix(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo.gametime")));
		gametime.setSuffix(ChatColor.WHITE + " " + toMMSS(duel.getTime()));
		obj.getScore(ChatColor.RED.toString()).setScore(8);
		
		Score blank2 = obj.getScore(ChatColor.UNDERLINE.toString());
		blank2.setScore(7);
		
		Team alive = board.registerNewTeam("alive");
		alive.addEntry(ChatColor.BLUE.toString());
		alive.setPrefix(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo.alive-players")));
		alive.setSuffix(ChatColor.WHITE.toString() + " " + duel.getAlive().size());
		obj.getScore(ChatColor.BLUE.toString()).setScore(6);
		
		Score blank3 = obj.getScore(ChatColor.STRIKETHROUGH.toString());
		blank3.setScore(5);
		
		Team refill = board.registerNewTeam("refill");
		refill.addEntry(ChatColor.YELLOW.toString());
		refill.setPrefix(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo.refill-timer")));
		refill.setSuffix(ChatColor.WHITE + " " + toMMSS(duel.getArena().getRefillTimer()));
		obj.getScore(ChatColor.YELLOW.toString()).setScore(4);
		
		Score blank4 = obj.getScore(ChatColor.ITALIC.toString());
		blank4.setScore(3);
		
		Team spec = board.registerNewTeam("spec");
		spec.addEntry(ChatColor.GREEN.toString());
		spec.setPrefix(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo.spectators")));
		spec.setSuffix(ChatColor.WHITE.toString() + " " + duel.getArena().getSpectators().size());
		obj.getScore(ChatColor.GREEN.toString()).setScore(2);
		
		Score blank5 = obj.getScore(ChatColor.MAGIC.toString());
		blank5.setScore(1);
		
		Score website = obj.getScore(ChatColor.translateAlternateColorCodes('&', SkyWars.getInstance().getConfig().getString("scoreboard-solo.website")));
		website.setScore(0);
		
		return board;
	}
	
	public void spectateSolo(Player player) {
		
	}
	
	public void spawn(Player player) {
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}
	
	public static String toMMSS(long dura){
		int minute = (int)(dura / 60.0D);
		long second = dura - (minute * 60);
		String formatted = "";
		{
			if(minute < 10){
				formatted += "";
			}
			formatted += minute;
			formatted += ":";
			if(second < 10){
				formatted += "0";
			}
			formatted += second;
		}
		return formatted;
	}
	
	public void updateTime(Player player, Scoreboard board, String name, int suffix) {
		
		if(board.getObjective(DisplaySlot.SIDEBAR) != null) {
			
			Team team = board.getTeam(name);
			
			if(team != null) {
				team.setSuffix(ChatColor.WHITE.toString() + " " + toMMSS(suffix));
			}
		}
	}
	
	public void update(Player player, Scoreboard board, String name, String suffix) {
		
		if(board.getObjective(DisplaySlot.SIDEBAR) != null) {
			
			Team team = board.getTeam(name);
			
			if(team != null) {
				team.setSuffix(ChatColor.WHITE.toString() + " " + suffix);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		
		for(Duel duel : DuelManager.getManager().getDules()) {
			
			duel.setTime(duel.getTime() + 1);
			
			duel.getArena().setRefillTimer(duel.getArena().getRefillTimer() - 1);
		
			if(duel.getArena().getRefillTimer() == 0) {
				duel.getArena().setRefillTimer(-1);
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(duel.getArena().getPlayers().contains(p.getName()) || duel.getArena().getSpectators().contains(p.getName())) {
						p.sendMessage(ChatColor.DARK_AQUA + "All chests have been refilled!");
					}
				}
			}
			
			if(duel.getArena().getRefillTimer() < 0) {
				duel.getArena().setRefillTimer(-1);
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(duel.getArena().getPlayers().contains(p.getName())/* || duel.getArena().getSpectators().contains(p.getName())*/) {
						//update(p, p.getScoreboard(), "refill", "0:00");
					}
				}
				
				
			}
			
			
		}
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			if(DuelManager.getManager().isPlayerInDuel(p)) {
				updateTime(p, p.getScoreboard(), "gametime", DuelManager.getManager().getDuelByPlayer(p).getTime());
				
				if(DuelManager.getManager().getDuelByPlayer(p).getArena().getRefillTimer() >= 0) {
					updateTime(p, p.getScoreboard(), "refill", DuelManager.getManager().getDuelByPlayer(p).getArena().getRefillTimer());
				}
				
				update(p, p.getScoreboard(), "alive", DuelManager.getManager().getDuelByPlayer(p).getAlive().size() + "");
				update(p, p.getScoreboard(), "spec", DuelManager.getManager().getDuelByPlayer(p).getArena().getSpectators().size() + "");
			}
			
			if(!DuelManager.getManager().isPlayerInDuel(p) && ArenaManager.getManager().isPlayerInArena(p)) {
				update(p, p.getScoreboard(), "players", ArenaManager.getManager().getArenaByPlayer(p).getPlayers().size() + "");
				updateTime(p, p.getScoreboard(), "start", ArenaManager.getManager().getArenaByPlayer(p).getTime());
			}
			
		}
		
	}
	
}
