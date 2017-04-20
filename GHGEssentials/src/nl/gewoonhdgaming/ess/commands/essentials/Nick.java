package nl.gewoonhdgaming.ess.commands.essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.gewoonhdgaming.ess.Main;
import nl.gewoonhdgaming.ess.files.PlayerData;
import nl.gewoonhdgaming.ess.utils.ChatUtils;

public class Nick implements CommandExecutor {
	
	private PlayerData playerData;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.prefix + ChatColor.RED + "Je moet een speler zijn om dit commando uit te voeren!");
			return false;
		}
		Player p = (Player) sender;
		if(args.length == 0) {
			if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.commands.nick"))) {
				p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Verkeerd gebruik van command! Gebruik: /nick <naam> of /nick <speler> <nick>");
			} else {
				p.sendMessage(ChatUtils.chatColor(Main.getPlugin().getConfig().getString("Messages.commands.permissiondeny")));
			}
		}
		if(args.length == 1) {
			if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.commands.nick"))) {
				playerData.getConfig().set(playerData.getConfig().getString("Players." + p.getUniqueId() + ".displayname"), args[0].toString());
				p.setDisplayName(ChatUtils.chatColor(args[0].toString()));
				p.sendMessage(ChatUtils.prefix + "Je naam is veranderd naar: " + ChatUtils.chatColor(args[0].toString()));
			} else {
				p.sendMessage(ChatUtils.chatColor(Main.getPlugin().getConfig().getString("Messages.commands.permissiondeny")));
			}
		}
		if(args.length == 2) {
			if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.commands.nick"))) {
				if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.commands.nick_anderen"))) {
					Player t = Bukkit.getPlayer(args[0]);
					if(t == null) {
						p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Speler bestaat niet of is niet online!");
						return false;
					}
					t.sendMessage(ChatUtils.prefix + "Je naam is veranderd naar: " + ChatUtils.chatColor(args[1].toString()));
					t.setDisplayName(ChatUtils.chatColor(args[1].toString()));
					playerData.getConfig().set(playerData.getConfig().getString("Players." + t.getUniqueId() + ".displayname"), args[1].toString());

				}
			} else {
				p.sendMessage(ChatUtils.chatColor(Main.getPlugin().getConfig().getString("Messages.commands.permissiondeny")));
			}
		}
		return false;
	}

}
