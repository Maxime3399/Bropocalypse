package fr.Maxime3399.Bropocalypse.scoreboards;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.Maxime3399.Bropocalypse.events.JoinEvents;

public class WaitScoreboard {
	
	private static Scoreboard s = Bukkit.getScoreboardManager().getMainScoreboard();
	
	public static void loadScoreboard(Player p) {
		
	    Objective o = null;
	    ArrayList<Score> list = new ArrayList<>();
	    if(s.getObjective("WaitSb") != null){
	    	o = s.getObjective("WaitSb");
	    	o.unregister();
	    }
	    
    	o = s.registerNewObjective("WaitSb", "dummy");
		
		o.setDisplayName("§3§lBROPOCALYPSE");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score s1 = o.getScore("§O");
		s1.setScore(4);
		list.add(s1);
		
		String time = "§4§lX";
		if(JoinEvents.getTimer() != 61) {
			if(JoinEvents.getTimer() > 59) {
	    		time = new SimpleDateFormat("m:ss").format(JoinEvents.getTimer()*1000);
			}else  if(JoinEvents.getTimer() > 9){
				time = new SimpleDateFormat("ss").format(JoinEvents.getTimer()*1000);
			}else {
				time = new SimpleDateFormat("s").format(JoinEvents.getTimer()*1000);
			}
		}
		Score s2 = o.getScore("§aDébut : "+time);
		s2.setScore(3);
		list.add(s2);
		
		Score s3 = o.getScore("§aJoueurs : "+Bukkit.getOnlinePlayers().size()+"/10");
		s3.setScore(2);
		list.add(s3);
		
		Score s4 = o.getScore("§1");
		s4.setScore(1);
		list.add(s4);
		
	    Score s5 = o.getScore("  §6§otest.maxime3399.fr");
	    s5.setScore(0);
	    list.add(s5);
	    
	    p.setScoreboard(s);
	    for(Score ss : list) {
	    	s.resetScores(ss.toString());
	    }
		
	}
	
}
