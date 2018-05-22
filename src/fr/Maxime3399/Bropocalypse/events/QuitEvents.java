package fr.Maxime3399.Bropocalypse.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.Maxime3399.Bropocalypse.managers.PlayersManager;

public class QuitEvents implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		PlayersManager.removePlayer(p);
		
	}

}
