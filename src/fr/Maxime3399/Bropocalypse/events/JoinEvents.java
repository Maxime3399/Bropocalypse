package fr.Maxime3399.Bropocalypse.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;

import fr.Maxime3399.Bropocalypse.MainClass;
import fr.Maxime3399.Bropocalypse.custom.CustomPlayer;
import fr.Maxime3399.Bropocalypse.custom.GameState;
import fr.Maxime3399.Bropocalypse.custom.Mode;
import fr.Maxime3399.Bropocalypse.custom.Role;
import fr.Maxime3399.Bropocalypse.custom.Team;
import fr.Maxime3399.Bropocalypse.managers.MapManager;
import fr.Maxime3399.Bropocalypse.managers.PlayersManager;
import fr.Maxime3399.Bropocalypse.scoreboards.WaitScoreboard;
import fr.Maxime3399.Bropocalypse.setter.InventorySetter;
import fr.Maxime3399.Bropocalypse.utils.ExpUtils;
import fr.Maxime3399.Bropocalypse.utils.MySQLUtils;

public class JoinEvents implements Listener {
	
	private static int task;
	private static int timer = 61;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		MySQLUtils.execute("INSERT INTO `bropocalypse_players` (`uuid`, `win`, `loses`, `kills_knife`, `kills_canon`, `deaths`, `time_hours`, `time_minutes`, `time_seconds`) VALUES ('"+p.getUniqueId().toString()+"', '0', '0', '0', '0', '0', '0', '0', '0');", true);
		
		if(GameState.isState(GameState.START)) {
			
			e.setJoinMessage(null);
			p.kickPlayer("§cLe serveur est en cours de démarrage !\n \n§6Serveur prêt dans §l"+MainClass.getStarter()+"§r§6 secondes.");
			
		}else if(GameState.isState(GameState.WAITING)) {
			
			e.setJoinMessage("§6§l[§r§3Bropocalypse§6§l]§r §6"+p.getName()+"§e a rejoint la partie §l[§r§e"+Bukkit.getOnlinePlayers().size()+"§d§l/§r§e10§l]");
			for(Player pls : Bukkit.getOnlinePlayers()) {
				WaitScoreboard.loadScoreboard(pls);
			}
			
			CustomPlayer cp = PlayersManager.addPlayer(p);
			cp.setRole(Role.PLAYER);
			
			p.setMaxHealth(20);
			p.setHealth(20);
			p.setFoodLevel(21);
			p.setGameMode(GameMode.ADVENTURE);
			
			InventorySetter.setWaitInventory(p);
			
			p.teleport(MapManager.getConnectLocation());
			
			int onlinePlayers = Bukkit.getOnlinePlayers().size();
			
			// 6
			if(onlinePlayers == 2) {
				
				task = Bukkit.getScheduler().scheduleSyncRepeatingTask(MainClass.getInstance(), new Runnable() {
					
					@SuppressWarnings("deprecation")
					@Override
					public void run() {
						
						setTimer(getTimer() - 1);
						for(Player pls : Bukkit.getOnlinePlayers()) {
							WaitScoreboard.loadScoreboard(pls);
							ExpUtils.setExpBar(pls, timer, 61);
							Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new Runnable() {
								@Override
								public void run() {
									ExpUtils.setExpBar(pls, (float) timer-0.5, 61);
								}
							}, 10);
							pls.setLevel(timer);
						}
						
						if(timer == 60 || timer == 30 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
							
							for(Player pls : Bukkit.getOnlinePlayers()) {
								pls.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §eDébut de la partie dans "+timer+" secondes");
								pls.playSound(pls.getLocation(), Sound.WOOD_CLICK, 100, 1);
							}
							
						}else if(timer == 0) {
							
							GameState.setState(GameState.PLAYING);
							cancelTimer();
							
							for(Player pls : Bukkit.getOnlinePlayers()) {
								
								CustomPlayer cps = PlayersManager.getCustomPlayer(pls);
								
								if(cps.getMode() == Mode.BUILD) {
									
									pls.setGameMode(GameMode.ADVENTURE);
									cps.setMode(Mode.DEFAULT);
									
								}
								
								if(cps.getTeam() == null) {
									
									int countBlue = 0;
									for(Player pls2 : Bukkit.getOnlinePlayers()) {
										CustomPlayer cps2 = PlayersManager.getCustomPlayer(pls2);
										if(cps2.getTeam() == Team.BLUE) {
											countBlue = countBlue+1;
										}
									}
									
									int countRed = 0;
									for(Player pls2 : Bukkit.getOnlinePlayers()) {
										CustomPlayer cps2 = PlayersManager.getCustomPlayer(pls2);
										if(cps2.getTeam() == Team.RED) {
											countRed = countRed+1;
										}
									}
									
									Scoreboard sc = Bukkit.getScoreboardManager().getMainScoreboard();
									org.bukkit.scoreboard.Team tRed = sc.getTeam("00000Red");
									org.bukkit.scoreboard.Team tBlue = sc.getTeam("00001Blue");
									
									if(countBlue >= countRed) {
										
										cps.setTeam(Team.RED);
										if(tBlue.getPlayers().contains(pls)) {
											tBlue.removePlayer(p);
										}
										tRed.addPlayer(pls);
										pls.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §eTu as rejoint l'équipe §c§lrouge§r §e!");
										
									}else {
										
										cps.setTeam(Team.BLUE);
										if(tRed.getPlayers().contains(pls)) {
											tRed.removePlayer(pls);
										}
										tBlue.addPlayer(pls);
										pls.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §eTu as rejoint l'équipe §9§lbleu§r §e!");
										
									}
									
								}
								
								InventorySetter.setGameInventory(pls);
								if(cps.getTeam() == Team.BLUE) {
									pls.teleport(MapManager.getBlueLocation());
								}else {
									pls.teleport(MapManager.getRedLocation());
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
