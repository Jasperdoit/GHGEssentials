package nl.gewoonhdgaming.ess.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
	
	public static String prefix = "";
	
	public static String chatColor(String msg) {
		 String colored = ChatColor.translateAlternateColorCodes('&', msg);
		return colored;	
	}

}
