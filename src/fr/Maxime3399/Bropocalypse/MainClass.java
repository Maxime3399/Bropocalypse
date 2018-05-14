package fr.Maxime3399.Bropocalypse;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.Maxime3399.Bropocalypse.utils.MySQLUtils;

public class MainClass extends JavaPlugin{
	
	private static Plugin plugin;
	
	public void onEnable() {
		
		plugin = this;
		
		File f = new File(getDataFolder(), "config.yml");
		if(!f.exists()) {
			
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			
			Bukkit.getConsoleSender().sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §eLe fichier de configuration \"config.yml\" a été créé. Merci de configurer le plugin.");
			disable();
			
		}else {
			
			if(getConfiguration().getBoolean("Database.Enable")) {
				
				if(MySQLUtils.connect()) {
					
					start();
					
				}else {
					
					Bukkit.getConsoleSender().sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §cConnexion a la base de données impossible.");
					disable();
					
				}
				
			}else {
				
				start();
				
			}
			
		}
		
	}
	
	private static void start() {
		
		//Start
		
	}
	
	public static Configuration getConfiguration() {
		
		return plugin.getConfig();
		
	}
	
	public static Plugin getInstance() {
		
		return plugin;
		
	}
	
	private static void disable() {
		
		Bukkit.getPluginManager().disablePlugin(plugin);
		
	}

}
