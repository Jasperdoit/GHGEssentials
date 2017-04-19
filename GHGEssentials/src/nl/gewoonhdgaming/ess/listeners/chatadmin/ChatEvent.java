package nl.gewoonhdgaming.ess.listeners.chatadmin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;
import nl.gewoonhdgaming.ess.Main;
import nl.gewoonhdgaming.ess.utils.ChatManagerUtils;
import nl.gewoonhdgaming.ess.utils.ChatUtils;

public class ChatEvent implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(ChatManagerUtils.AdminChat == true) {
			if(!p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.chatAdmin.admin"))) {
				e.setCancelled(true);
				p.sendMessage(ChatUtils.prefix + ChatColor.RED + "Je mag niet praten omdat AdminChat aan staat!");
				return;
			}
			e.setCancelled(true);
			
			ChatUtils.broadcast(ChatColor.AQUA + "STAFF " + p.getName() + ChatColor.GREEN +  " > " + e.getMessage());
			
		}
	}
	
	@EventHandler
	public void slowChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(ChatManagerUtils.slowChat == true) {
			if(!p.hasPermission(Main.getPlugin().getConfig().getString("Permissions.chatAdmin.admin"))) {
				if(ChatManagerUtils.has(p)) {
					e.setCancelled(true);
				} else {
					ChatManagerUtils.add(p, Main.getPlugin().getConfig().getInt("chatAdmin.cooldown"));
				}
			}
		}
	}

}
