package fr.Maxime3399.Bropocalypse.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import fr.Maxime3399.Bropocalypse.MainClass;
import fr.Maxime3399.Bropocalypse.events.CancelEvents;
import fr.Maxime3399.Bropocalypse.events.InteractEvents;
import fr.Maxime3399.Bropocalypse.events.JoinEvents;
import fr.Maxime3399.Bropocalypse.events.QuitEvents;

public class EventsManager {
	
	public static void registerEvents() {
		
		Plugin p = MainClass.getInstance();
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new JoinEvents(), p);
		pm.registerEvents(new QuitEvents(), p);
		pm.registerEvents(new CancelEvents(), p);
		pm.registerEvents(new InteractEvents(), p);
		
	}

}
