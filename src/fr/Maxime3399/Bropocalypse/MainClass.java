package fr.Maxime3399.Bropocalypse;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import fr.Maxime3399.Bropocalypse.custom.GameState;
import fr.Maxime3399.Bropocalypse.managers.EventsManager;
import fr.Maxime3399.Bropocalypse.managers.MapManager;
import fr.Maxime3399.Bropocalypse.utils.MySQLUtils;

public class MainClass extends JavaPlugin{
	
	private static Plugin plugin;
	private static int task;
	private static int starter = 11;
	
	public void onEnable() {
		
		plugin = this;
		GameState.setState(GameState.START);
		MapManager.selectMap();
		
		File f = new File(getDataFolder(), "config.yml");
		if(!f.exists()) {
			
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			
			Bukkit.getConsoleSender().sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §eLe fichier de configuration \"config.yml\" a été créé. Merci de configurer le plugin.");
			disable();
			
		}else {
			
			if(getConfiguration().getBoolean("Database.Enable")) {
				
				if(MySQLUtils.connect()) {
					
					if(MySQLUtils.execute("CREATE TABLE IF NOT EXISTS `test`.`bropocalypse_players` ( `uuid` VARCHAR(255) NOT NULL , `win` INT(255) NOT NULL , `loses` INT(255) NOT NULL , `kills_knife` INT(255) NOT NULL , `kills_canon` INT(255) NOT NULL , `deaths` INT(255) NOT NULL , `time_hours` INT(255) NOT NULL , `time_minutes` INT(255) NOT NULL , `time_seconds` INT(255) NOT NULL ) ENGINE = MyISAM;", false)) {
						
						start();
						
					}else {
						
						Bukkit.getConsoleSender().sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §cCréation des tables de la base de données impossible.");
						disable();
						
					}
					
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
		
		Bukkit.getConsoleSender().sendMessage("§6§l[§r§3Bropocalypse§6§l]§r §aLe plugin a correctement démarré !");
		EventsManager.registerEvents();
		Scoreboard sc = Bukkit.getScoreboardManager().getMainScoreboard();
		org.bukkit.scoreboard.Team tRed = sc.registerNewTeam("00000Red");
		org.bukkit.scoreboard.Team tBlue = sc.registerNewTeam("00001Blue");
		tRed.setPrefix("§c");
		tBlue.setPrefix("§9");
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				
				starter--;
				
				if(starter == 0) {
					
					Bukkit.getScheduler().cancelTask(task);
					GameState.setState(GameState.WAITING);
					
				}
				
			}
			
		}, 20, 20);
		
	}
	
	public void onDisable() {
		
		for(Player pls : Bukkit.getOnlinePlayers()) {
			
			pls.kickPlayer("§6Redémarrage du serveur");
			
		}
		
		Scoreboard sc = Bukkit.getScoreboardManager().getMainScoreboard();
		sc.getTeam("00000Red").unregister();
		sc.getTeam("00001Blue").unregister();
		
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

	public static int getStarter() {
		return starter;
	}

	public static void setStarter(int starter) {
		MainClass.starter = starter;
	}

}
