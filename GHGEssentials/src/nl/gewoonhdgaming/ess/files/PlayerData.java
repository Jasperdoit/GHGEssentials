package nl.gewoonhdgaming.ess.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import nl.gewoonhdgaming.ess.Main;

public class PlayerData extends AbstractFile{
	
	public PlayerData(Main main) {
		super(main, "playerdata.yml");
	}
	
	public void newPlayer(Player p) {
		config.set("Players." + p.getUniqueId() + ".name", p.getName());
		config.set("Players." + p.getUniqueId() + ".displayname", p.getName());
	}
	
	public boolean isInConfigPD(Player p) {
		if (config.contains("Players." + p.getUniqueId())) {
			return true;
		} 
		return false;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}

}
