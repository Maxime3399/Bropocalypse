package fr.Maxime3399.Bropocalypse.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class MapManager {
	
	private static String map;
	
	public static void selectMap() {
		
		map = "Azteque";
		
	}
	
	public static Location getConnectLocation() {
		
		Location loc = null;
		
		if(map.equalsIgnoreCase("Azteque")) {
			
			loc = new Location(Bukkit.getWorld("Azteque"), 13.5, 88.1, 20.5);
			loc.setYaw(-180);
			
		}
		
		return loc;
		
	}
	
	public static Location getBlueLocation() {
		
		Location loc = null;
		
		if(map.equalsIgnoreCase("Azteque")) {
			
			loc = new Location(Bukkit.getWorld("Azteque"), 13.5, 98.1, 48.5);
			loc.setYaw(-180);
			
		}
		
		return loc;
		
	}
	
	public static Location getRedLocation() {
		
		Location loc = null;
		
		if(map.equalsIgnoreCase("Azteque")) {
			
			loc = new Location(Bukkit.getWorld("Azteque"), 13.5, 98.1, -28.5);
			
		}
		
		return loc;
		
	}

}
