package fr.Maxime3399.Bropocalypse.setter;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import fr.Maxime3399.Bropocalypse.MainClass;
import fr.Maxime3399.Bropocalypse.custom.CustomPlayer;
import fr.Maxime3399.Bropocalypse.custom.Team;
import fr.Maxime3399.Bropocalypse.managers.PlayersManager;

public class InventorySetter {
	
	public static void setWaitInventory(Player p) {
		
		Inventory i = p.getInventory();
		i.clear();
		p.getInventory().setArmorContents(null);
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
		
		ItemStack ISblue = new ItemStack(Material.WOOL, 1, (byte) 11);
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
	
	public static void setGameInventory(Player p) {
		
		Inventory i = p.getInventory();
		i.clear();
		p.getInventory().setArmorContents(null);
		CustomPlayer cp = PlayersManager.getCustomPlayer(p);
		
		ItemStack IScanon = new ItemStack(Material.WOOD_HOE);
		ItemMeta IMcanon = IScanon.getItemMeta();
		IMcanon.setDisplayName("§c§lBazooka");
		IScanon.setItemMeta(IMcanon);
		p.getInventory().setItem(0, IScanon);
		
		ItemStack ISknife = new ItemStack(Material.FEATHER);
		ItemMeta IMknife = ISknife.getItemMeta();
		IMknife.setDisplayName("§b§lCouteau");
		ISknife.setItemMeta(IMknife);
		p.getInventory().setItem(1, ISknife);
		
		Color col = Color.BLUE;
		if(cp.getTeam() == Team.RED) {
			col = Color.RED;
		}
		
		ItemStack IShelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta LAMhelmet = (LeatherArmorMeta) IShelmet.getItemMeta();
		LAMhelmet.setColor(col);
		IShelmet.setItemMeta(LAMhelmet);
		p.getInventory().setHelmet(IShelmet);
		
		ItemStack ISchestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta LAMchestplate = (LeatherArmorMeta) ISchestplate.getItemMeta();
		LAMchestplate.setColor(col);
		ISchestplate.setItemMeta(LAMchestplate);
		p.getInventory().setChestplate(ISchestplate);
		
		ItemStack ISpant = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta LAMpant = (LeatherArmorMeta) ISpant.getItemMeta();
		LAMpant.setColor(col);
		ISpant.setItemMeta(LAMpant);
		p.getInventory().setLeggings(ISpant);
		
		ItemStack ISboots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta LAMboots = (LeatherArmorMeta) ISboots.getItemMeta();
		LAMboots.setColor(col);
		ISboots.setItemMeta(LAMboots);
		p.getInventory().setBoots(ISboots);
		
	}

}
