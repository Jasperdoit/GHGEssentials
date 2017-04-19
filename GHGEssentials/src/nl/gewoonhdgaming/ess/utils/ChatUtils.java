package nl.gewoonhdgaming.ess.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import nl.gewoonhdgaming.ess.Main;

public class ChatUtils {
	
	public static String prefix = ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("Messages.prefix")) + " ";
	
	public static String chatColor(String msg) {
		 String colored = ChatColor.translateAlternateColorCodes('&', msg);
		return colored;	
	}
	
	public static void broadcast(String msg) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
		}
	}

}
