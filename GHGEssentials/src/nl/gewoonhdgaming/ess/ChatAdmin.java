package nl.gewoonhdgaming.ess;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.gewoonhdgaming.ess.utils.ChatManagerUtils;
import nl.gewoonhdgaming.ess.utils.ChatUtils;

public class ChatAdmin implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("chatadmin")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatUtils.prefix + ChatColor.RED + "Je moet een speler zijn om dit commando uit te voeren!");
				return false;
			}
			Player p = (Player) sender;
				
			if(args.length >= 2) {
				
			} 
			
			else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("adminchat")) {
					if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.chatAdmin.admin"))) {
					ChatManagerUtils.AdminChat = !ChatManagerUtils.AdminChat;
					
					if(ChatManagerUtils.AdminChat == true) {
					p.sendMessage(ChatUtils.prefix + "Adminchat is ingeschakeld!");
					ChatUtils.broadcast("&cAdminchat is ingeschakeld, door: " + p.getName());
					}
					
					if(ChatManagerUtils.AdminChat == false) {
						p.sendMessage(ChatUtils.prefix + "Adminchat is uitgeschakeld!");
						ChatUtils.broadcast("&2Adminchat is uitgeschakeld, door: " + p.getName());
						}
					} else {
						p.sendMessage(ChatUtils.prefix + ChatUtils.chatColor(Main.getPlugin().getConfig().getString("Messages.commands.permissiondeny")));
					}
				}
				if(args[0].equalsIgnoreCase("slowchat")) {
					if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.chatAdmin.admin"))) {
					ChatManagerUtils.slowChat = !ChatManagerUtils.slowChat;
					
					if(ChatManagerUtils.slowChat == true) {
					p.sendMessage(ChatUtils.prefix + "Slowchat is ingeschakeld!");
					ChatUtils.broadcast("&cSlowchat is ingeschakeld, door: " + p.getName());
					}
					
					if(ChatManagerUtils.slowChat == false) {
						p.sendMessage(ChatUtils.prefix + "Slowchat is uitgeschakeld!");
						ChatUtils.broadcast("&2Slowchat is uitgeschakeld, door: " + p.getName());
						}
					} else {
						p.sendMessage(ChatUtils.prefix + ChatUtils.chatColor(Main.getPlugin().getConfig().getString("Messages.commands.permissiondeny")));
					}
				}
			}
			else if(args.length == 0) {
				if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.chatAdmin.admin"))) {
				p.sendMessage(ChatUtils.prefix + ChatUtils.chatColor("&6&s---------&r&b&lChatAdmin&r&6&s---------"));
				p.sendMessage(ChatUtils.prefix + "/chatadmin adminchat" + ChatColor.GOLD + " - " + ChatColor.RESET + "Zorg ervoor dat alleen admins kunnen praten");
				p.sendMessage(ChatUtils.prefix + "/chatadmin slowchat" + ChatColor.GOLD + " - " + ChatColor.RESET + "Laat spelers praten om de zoveel secondes!");
				}
			}
			
		}
		return false;
	}

}
