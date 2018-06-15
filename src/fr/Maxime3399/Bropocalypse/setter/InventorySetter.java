package fr.Maxime3399.Bropocalypse.setter;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.Maxime3399.Bropocalypse.MainClass;
import fr.Maxime3399.Bropocalypse.custom.CustomPlayer;
import fr.Maxime3399.Bropocalypse.custom.Team;
import fr.Maxime3399.Bropocalypse.managers.PlayersManager;

public class InventorySetter {
	
	public static void setWaitInventory(Player p) {
		
		Inventory i = p.getInventory();
		i.clear();
		CustomPlayer cp = PlayersManager.getCustomPlayer(p);
		
		ItemStack ISred = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta IMred = ISred.getItemMeta();
		if(cp.getTeam() == Team.RED) {
			IMred.setDisplayName("§c§lRouge");
			IMred.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
			IMred.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}else {
			IMred.setDisplayName("§c§lRouge§r §7( clique droit )");
		}
		ISred.setItemMeta(IMred);
		p.getInventory().setItem(1, ISred);
		
		ItemStack ISblue = new ItemStack(Material.WOOL, 1, (byte) 14);
		ItemMeta IMblue = ISblue.getItemMeta();
		if(cp.getTeam() == Team.BLUE) {
			IMblue.setDisplayName("§9§lBleu");
			IMblue.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
			IMblue.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}else {
			IMblue.setDisplayName("§9§lBleu§r §7( clique droit )");
		}
		ISblue.setItemMeta(IMblue);
		p.getInventory().setItem(2, ISblue);
		
		if(MainClass.getConfiguration().getBoolean("Database.Enable")) {
			
			ItemStack ISstats = new ItemStack(Material.PAPER);
			ItemMeta IMstats = ISstats.getItemMeta();
			IMstats.setDisplayName("§b§lStatistiques§r §7( clique droit )");
			ISstats.setItemMeta(IMstats);
			p.getInventory().setItem(4, ISstats);
			
		}
		
	}

}
