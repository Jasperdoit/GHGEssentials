package nl.gewoonhdgaming.ess.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.gewoonhdgaming.ess.Main;
import nl.gewoonhdgaming.ess.utils.ChatUtils;

public class Join implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!p.hasPlayedBefore()) {
			if(Main.getPlugin().getConfig().getBoolean("Messages.join.allow.first")) {
				String msg = Main.getPlugin().getConfig().getString("Messages.join.first");
				ChatUtils.chatColor(msg);
			}
		} else {
			if(Main.getPlugin().getConfig().getBoolean("Messages.join.allow.other")) {
				String msg = Main.getPlugin().getConfig().getString("Messages.join.other");
				ChatUtils.chatColor(msg);
			}
		}
	}

}
