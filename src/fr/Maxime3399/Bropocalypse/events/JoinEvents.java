package fr.Maxime3399.Bropocalypse.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.Maxime3399.Bropocalypse.MainClass;
import fr.Maxime3399.Bropocalypse.custom.CustomPlayer;
import fr.Maxime3399.Bropocalypse.custom.GameState;
import fr.Maxime3399.Bropocalypse.custom.Mode;
import fr.Maxime3399.Bropocalypse.custom.Role;
import fr.Maxime3399.Bropocalypse.managers.PlayersManager;
import fr.Maxime3399.Bropocalypse.setter.InventorySetter;

public class JoinEvents implements Listener {
	
	private static int task;
	private static int timer = 61;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(GameState.isState(GameState.START)) {
			
			p.kickPlayer("§cLe serveur est en cours de démarrage !\n \n§6Serveur prêt dans §l"+MainClass.getStarter()+"§r§6 secondes.");
			
		}else if(GameState.isState(GameState.WAITING)) {
			
			CustomPlayer cp = PlayersManager.addPlayer(p);
			cp.setRole(Role.PLAYER);
			
			p.setMaxHealth(20);
			p.setHealth(20);
			p.setFoodLevel(21);
			p.setGameMode(GameMode.ADVENTURE);
			
			InventorySetter.setWaitInventory(p);
			
			//TP
			
			int onlinePlayers = Bukkit.getOnlinePlayers().size();
			
			if(onlinePlayers == 5) {
				
				task = Bukkit.getScheduler().scheduleSyncRepeatingTask(MainClass.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						
						setTimer(getTimer() - 1);
						
						if(timer == 60 || timer == 30 || timer == 10 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
							
							for(Player pls : Bukkit.getOnlinePlayers()) {
								pls.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §eDébut de la partie dans "+timer+" secondes");
								pls.playSound(pls.getLocation(), Sound.WOOD_CLICK, 100, 1);
							}
							
						}else if(timer == 0) {
							
							for(Player pls : Bukkit.getOnlinePlayers()) {
								
								CustomPlayer cps = PlayersManager.getCustomPlayer(pls);
								
								if(cps.getMode() == Mode.BUILD) {
									
									pls.setGameMode(GameMode.ADVENTURE);
									//inventory game
									cps.setMode(Mode.DEFAULT);
									
								}
								
							}
							
						}
						
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
