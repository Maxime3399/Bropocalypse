package fr.Maxime3399.Bropocalypse.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;

import fr.Maxime3399.Bropocalypse.MainClass;
import fr.Maxime3399.Bropocalypse.custom.GameState;
import fr.Maxime3399.Bropocalypse.managers.PlayersManager;
import fr.Maxime3399.Bropocalypse.scoreboards.WaitScoreboard;

public class QuitEvents implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		Scoreboard sc = Bukkit.getScoreboardManager().getMainScoreboard();
		org.bukkit.scoreboard.Team tRed = sc.getTeam("00000Red");
		org.bukkit.scoreboard.Team tBlue = sc.getTeam("00001Blue");
		if(tBlue.getPlayers().contains(p)) {
			tBlue.removePlayer(p);
		}
		if(tRed.getPlayers().contains(p)) {
			tRed.removePlayer(p);
		}
		
		if(GameState.isState(GameState.WAITING)) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					
					for(Player pls : Bukkit.getOnlinePlayers()) {
						WaitScoreboard.loadScoreboard(pls);
					}
					
				}
				
			}, 5);
			
		}
		
		if(!GameState.isState(GameState.START)) {
			
			PlayersManager.removePlayer(p);
			
		}
		
	}

}
