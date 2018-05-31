package fr.Maxime3399.Bropocalypse.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.Maxime3399.Bropocalypse.MainClass;
import fr.Maxime3399.Bropocalypse.custom.GameState;
import fr.Maxime3399.Bropocalypse.managers.PlayersManager;

public class JoinEvents implements Listener {
	
	private static int task;
	private static int timer = 61;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(GameState.isState(GameState.START)) {
			
			p.kickPlayer("§cLe serveur est en cours de démarrage !\n \n§6Serveur prêt dans §l"+MainClass.getStarter()+"§r§6 secondes.");
			
		}else if(GameState.isState(GameState.WAITING)) {
			
			PlayersManager.addPlayer(p);
			
			int onlinePlayers = Bukkit.getOnlinePlayers().size();
			
			if(onlinePlayers == 5) {
				
				task = Bukkit.getScheduler().scheduleSyncRepeatingTask(MainClass.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						
						setTimer(getTimer() - 1);
						
						//suite
						
					}
					
				}, 20, 20);
				
			}
			
		}
		
	}
	
	public static void cancelTimer() {
		
		Bukkit.getScheduler().cancelTask(task);
		setTimer(61);
		
	}

	public static int getTimer() {
		return timer;
	}

	public static void setTimer(int timer) {
		JoinEvents.timer = timer;
	}

}
