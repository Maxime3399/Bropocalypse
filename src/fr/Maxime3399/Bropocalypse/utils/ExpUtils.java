package fr.Maxime3399.Bropocalypse.utils;

import org.bukkit.entity.Player;

public class ExpUtils {
	
	public static void setExpBar(Player p, double d, float max) {
		
		float exp = (float) (d*100);
		exp = exp/max;
		exp = exp/100;
		
		p.setExp(exp);
		
	}

}
