package fr.Maxime3399.Bropocalypse.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.Maxime3399.Bropocalypse.custom.CustomPlayer;
import fr.Maxime3399.Bropocalypse.custom.GameState;
import fr.Maxime3399.Bropocalypse.custom.Mode;
import fr.Maxime3399.Bropocalypse.managers.PlayersManager;

public class CancelEvents implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		
		Player p = e.getPlayer();
		CustomPlayer cp = PlayersManager.getCustomPlayer(p);
		
		if(cp.getMode() != Mode.BUILD) {
			
			e.setCancelled(true);
			
		}
		
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		
		Player p = e.getPlayer();
		CustomPlayer cp = PlayersManager.getCustomPlayer(p);
		
		if(cp.getMode() != Mode.BUILD) {
			
			e.setCancelled(true);
			
		}
		
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		
		Player p = e.getPlayer();
		CustomPlayer cp = PlayersManager.getCustomPlayer(p);
		
		if(cp.getMode() != Mode.BUILD) {
			
			e.setCancelled(true);
			
		}
		
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		
		Player p = e.getPlayer();
		CustomPlayer cp = PlayersManager.getCustomPlayer(p);
		
		if(cp.getMode() != Mode.BUILD) {
			
			e.setCancelled(true);
			
		}
		
	}
	
	@EventHandler
	public void weatherChange(WeatherChangeEvent e) {
		
		e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		
		e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		
		if(!GameState.isState(GameState.PLAYING)) {
			
			e.setCancelled(true);
			
		}else if(e.getCause() == DamageCause.FALL){
			
			e.setCancelled(true);
			
		}
		
	}

}
