package nl.gewoonhdgaming.ess;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	static Main pl;
	
	@Override
	public void onEnable() {
		pl = this;
		
		this.getConfig().addDefault("Messages.prefix", "&f[&bGHG&6&lEssentials&f]");
		
		this.getConfig().options().copyDefaults(true);
		
		saveConfig();
	}
	
	@Override
	public void onDisable() {
		saveConfig();
	}
	
	public static Main getPlugin() {
		return pl;
	}

}

