package fr.Maxime3399.Bropocalypse.managers;

import java.util.ArrayList;

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
	
	public static ArrayList<Location> getSpawns(){
		
		ArrayList<Location > list = new ArrayList<>();
		
		if(map.equalsIgnoreCase("Azteque")) {
			
			Location l1 = new Location(Bukkit.getWorld("Azteque"), 13.5, 88.1, 20.5);
			list.add(l1);
			Location l2 = new Location(Bukkit.getWorld("Azteque"), 13.5, 95.1, 20.5);
			list.add(l2);
			
		}
			
		return list;
		
	}

}
