package nl.gewoonhdgaming.ess.commands.essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.gewoonhdgaming.ess.Main;
import nl.gewoonhdgaming.ess.utils.ChatUtils;

public class Teleport implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("teleport")) {
			if(!(sender instanceof Player)) {
			sender.sendMessage(ChatUtils.prefix + ChatColor.RED + "Je moet een speler zijn om dit commando uit te voeren!");
			return false;
			}
				Player p = (Player) sender;
				if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.commands.tp"))) {
					if(args.length == 0) {
						p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Verkeerd gebruik van command! Gebruik: /teleport <speler> [speler]");
						return false;
				}
					if(args.length == 1) {
						if(args[0] == null) {
							p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Speler bestaat niet of is niet online!");
							return false;
						}
						Player t = Bukkit.getPlayer(args[0]);
						if(t == null) {
							p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Speler bestaat niet of is niet online!");
							return false;
						}
						p.teleport(t.getLocation());
						p.sendMessage(ChatUtils.prefix + "Je bent geteleporteerd naar: " + t.getName());
					}
					if(args.length == 2) {
						if(p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.commands.tp_naar_iemand_anders"))) {
							if(args[0] == null) {
								p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Speler1 bestaat niet of is niet online!");
								return false;
							}
							Player t = Bukkit.getPlayer(args[0]);
							if(t == null) {
								p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Speler1 bestaat niet of is niet online!");
								return false;
							}
							if(args[1] == null) {
								p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Speler2 bestaat niet of is niet online!");
								return false;
							}
							Player t2 = Bukkit.getPlayer(args[1]);
							if(t2 == null) {
								p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Speler2 bestaat niet of is niet online!");
								return false;
							}
							t.teleport(t2.getLocation());
							t.sendMessage(ChatUtils.prefix + "Je bent geteleporteerd naar: " + t2.getName());
						} else {
							p.sendMessage(ChatUtils.chatColor(Main.getPlugin().getConfig().getString("Messages.commands.permissiondeny")));
						}
					}
			}else {
				p.sendMessage(ChatUtils.chatColor(Main.getPlugin().getConfig().getString("Messages.commands.permissiondeny")));
			}
		}
		return false;
	}

}
