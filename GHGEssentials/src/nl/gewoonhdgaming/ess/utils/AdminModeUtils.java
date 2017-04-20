package nl.gewoonhdgaming.ess.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class AdminModeUtils {
	
	public static HashMap<Player, ItemStack[]> ArmorSaves = new HashMap<Player, ItemStack[]>();
	public static HashMap<Player, ItemStack[]> inventory = new HashMap<Player, ItemStack[]>();
	public static HashMap<Player, GameMode> gamemode = new HashMap<>();
	public static ArrayList<Player> inAdminMode = new ArrayList<>();
	
	public static boolean containsAdminmode(Player p) {
		if(inAdminMode.contains(p)) {
			return true;
		}
		return false;
	}
	
	public static void addPlayer(Player p) {
		if(!containsAdminmode(p)) {
			inAdminMode.add(p);
			ArmorSaves.put(p, p.getInventory().getArmorContents());
			inventory.put(p, p.getInventory().getContents());
			gamemode.put(p, p.getGameMode());
			p.getInventory().clear();
			p.setGameMode(GameMode.CREATIVE);		
			p.hidePlayer((Player) ServerUtils.allPlayers);
			p.getInventory().setItem(1, addPlayerCompass());
		}
	}
	
	private static ItemStack addPlayerCompass() {
		ItemStack item = new ItemStack(Material.COMPASS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Speler kompas");
		meta.setLore(Arrays.asList(ChatColor.BLUE + "Teleporteer naar een speler!"));
		item.setItemMeta(meta);
		
		return item;
	}

}
