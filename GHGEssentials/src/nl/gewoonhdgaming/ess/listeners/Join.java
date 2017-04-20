package nl.gewoonhdgaming.ess.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.gewoonhdgaming.ess.Main;
import nl.gewoonhdgaming.ess.files.PlayerData;
import nl.gewoonhdgaming.ess.utils.ChatUtils;
import nl.gewoonhdgaming.ess.utils.ServerUtils;

public class Join implements Listener{
	
	private PlayerData playerData;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		ServerUtils.allPlayers.add(p);
		if(!playerData.isInConfigPD(p)) {
			playerData.newPlayer(p);
		}
		if(!p.hasPlayedBefore()) {
			if(Main.getPlugin().getConfig().getBoolean("Messages.join.allow.first")) {
				String msg = Main.getPlugin().getConfig().getString("Messages.join.first");
				p.sendMessage(ChatUtils.chatColor(msg));
				p.setDisplayName(ChatUtils.chatColor(playerData.getConfig().getString("Players." + p.getUniqueId() + ".displayname")));
			}
		} else {
			if(Main.getPlugin().getConfig().getBoolean("Messages.join.allow.other")) {
				String msg = Main.getPlugin().getConfig().getString("Messages.join.other");
				p.sendMessage(ChatUtils.chatColor(msg));
				p.setDisplayName(ChatUtils.chatColor(playerData.getConfig().getString("Players." + p.getUniqueId() + ".displayname")));
			}
		}
	}

}
