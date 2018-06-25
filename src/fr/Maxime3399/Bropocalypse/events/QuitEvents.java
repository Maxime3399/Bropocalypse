package fr.Maxime3399.Bropocalypse.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
		p.setExp(0);
		p.setLevel(0);
		
		if(GameState.isState(GameState.WAITING)) {
			
			int con = Bukkit.getOnlinePlayers().size()-1;
			e.setQuitMessage("§6§l[§r§3Bropocalypse§6§l]§r §6"+p.getName()+"§e a quitté §l[§r§e"+con+"§d§l/§r§e10§l]");
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					
					for(Player pls : Bukkit.getOnlinePlayers()) {
						
						WaitScoreboard.loadScoreboard(pls);
						
					}
					
					if(JoinEvents.getTimer() != 61 && Bukkit.getOnlinePlayers().size() < 2) {
						
						JoinEvents.cancelTimer();
						
						for(Player pls : Bukkit.getOnlinePlayers()) {
							
							pls.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §cIl n'y a plus assez de joueurs pour démarrer la partie !");
							pls.playSound(pls.getLocation(), Sound.DOOR_OPEN, 100, 1);
							pls.setLevel(0);
							pls.setExp(0);
							WaitScoreboard.loadScoreboard(pls);
							
						}
						
					}
					
				}
				
			}, 5);
			
		}
		
		if(!GameState.isState(GameState.START)) {
			
			PlayersManager.removePlayer(p);
			
		}
		
	}

}
