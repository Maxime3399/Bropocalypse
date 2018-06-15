package fr.Maxime3399.Bropocalypse.events;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Scoreboard;

import fr.Maxime3399.Bropocalypse.custom.CustomPlayer;
import fr.Maxime3399.Bropocalypse.custom.Team;
import fr.Maxime3399.Bropocalypse.managers.PlayersManager;
import fr.Maxime3399.Bropocalypse.setter.InventorySetter;

public class InteractEvents implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		CustomPlayer cp = PlayersManager.getCustomPlayer(p);
		
		if(e.getItem() == null || e.getAction() == null) {
			
			return;
			
		}else if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRouge")) {
				
				p.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §cTu es déjà dans l'équipe §lrouge§r§c !");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 100, 1);
				
			}else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9§lBleu")) {
				
				p.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §cTu es déjà dans l'équipe §9§lbleu§r§c !");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 100, 1);
				
			}else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lRouge§r §7( clique droit )")) {
				
				int count = 0;
				for(Player pls : Bukkit.getOnlinePlayers()) {
					CustomPlayer cps = PlayersManager.getCustomPlayer(pls);
					if(cps.getTeam() == Team.RED) {
						count = count+1;
					}
				}
				
				if(count >= 5) {
					
					p.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §cL'équipe §lrouge§r §cest complète !");
					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 100, 1);
					
				}else {
					
					cp.setTeam(Team.RED);
					p.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §eTu as rejoint l'équipe §c§lrouge§r §e!");
					p.playSound(p.getLocation(), Sound.PISTON_EXTEND, 100, 1);
					InventorySetter.setWaitInventory(p);
					
					Scoreboard sc = Bukkit.getScoreboardManager().getMainScoreboard();
					org.bukkit.scoreboard.Team tRed = sc.getTeam("00000Red");
					org.bukkit.scoreboard.Team tBlue = sc.getTeam("00001Blue");
					if(tBlue.getPlayers().contains(p)) {
						tBlue.removePlayer(p);
					}
					tRed.addPlayer(p);
					
				}
				
			}else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9§lBleu§r §7( clique droit )")) {
				
				int count = 0;
				for(Player pls : Bukkit.getOnlinePlayers()) {
					CustomPlayer cps = PlayersManager.getCustomPlayer(pls);
					if(cps.getTeam() == Team.BLUE) {
						count = count+1;
					}
				}
				
				if(count >= 5) {
					
					p.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §cL'équipe §9§lbleu§r §cest complète !");
					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 100, 1);
					
				}else {
					
					cp.setTeam(Team.BLUE);
					p.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §eTu as rejoint l'équipe §9§lbleu§r §e!");
					p.playSound(p.getLocation(), Sound.PISTON_EXTEND, 100, 1);
					InventorySetter.setWaitInventory(p);
					
					Scoreboard sc = Bukkit.getScoreboardManager().getMainScoreboard();
					org.bukkit.scoreboard.Team tRed = sc.getTeam("00000Red");
					org.bukkit.scoreboard.Team tBlue = sc.getTeam("00001Blue");
					if(tRed.getPlayers().contains(p)) {
						tRed.removePlayer(p);
					}
					tBlue.addPlayer(p);
					
				}
				
			}else if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lStatistiques§r §7( clique droit )")) {
				
				p.sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §cLes statistiques ne sont pas encore disponibles !");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 100, 1);
				
			}
			
		}
		
	}

}
