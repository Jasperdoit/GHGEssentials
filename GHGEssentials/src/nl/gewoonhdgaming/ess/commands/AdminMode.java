package nl.gewoonhdgaming.ess.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.gewoonhdgaming.ess.Main;
import nl.gewoonhdgaming.ess.utils.AdminModeUtils;
import nl.gewoonhdgaming.ess.utils.ChatUtils;
import nl.gewoonhdgaming.ess.utils.ServerUtils;

public class AdminMode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("adminmode")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatUtils.prefix + ChatColor.RED + "Je moet een speler zijn om dit commando uit te voeren!");
				return false;
			}
			Player p = (Player) sender;
			if(args.length == 0) {
				if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.adminmode.admin"))) {
					p.sendMessage(ChatUtils.prefix + ChatUtils.chatColor("&6&m---------&r&b&lAdminMode&r&6&m---------"));
					p.sendMessage(ChatUtils.prefix + "/adminmode toggle" + ChatColor.GOLD + " - " + ChatColor.RESET + "Schakel adminmode in/uit");
					}
			}
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("toggle")) {
					if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.adminmode.admin"))) {
						if(!AdminModeUtils.containsAdminmode(p)) {
							p.sendMessage(ChatUtils.prefix + "AdminMode ingeschakeld!");
							AdminModeUtils.addPlayer(p);
							
							return false;
						}
						if(AdminModeUtils.containsAdminmode(p)) {
							p.sendMessage(ChatUtils.prefix + "AdminMode uitgeschakeld!");
							p.getInventory().clear();
							p.getInventory().setContents(AdminModeUtils.inventory.get(p));
							p.setGameMode(AdminModeUtils.gamemode.get(p));
							p.getInventory().setArmorContents(AdminModeUtils.ArmorSaves.get(p));
							p.showPlayer((Player) ServerUtils.allPlayers);
							AdminModeUtils.inAdminMode.remove(p);
							AdminModeUtils.ArmorSaves.remove(p);
							AdminModeUtils.inventory.remove(p);
							AdminModeUtils.gamemode.remove(p);
							return false;
						}
					}else {
						p.sendMessage(ChatUtils.chatColor(Main.getPlugin().getConfig().getString("Messages.commands.permissiondeny")));
					}
				}
			}
			
		}
		return false;
	}
	
	

}
