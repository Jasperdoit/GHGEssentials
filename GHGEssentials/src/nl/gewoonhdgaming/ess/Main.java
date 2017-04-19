package nl.gewoonhdgaming.ess;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.gewoonhdgaming.ess.listeners.Join;

public class Main extends JavaPlugin {
	
	static Main pl;
	
	@Override
	public void onEnable() {
		pl = this;
		
		
		loadConfig();
	}
	
	@Override
	public void onDisable() {
		saveConfig();
	}
	
	public static Main getPlugin() {
		return pl;
	}
	
	public void loadConfig() {
		this.getConfig().addDefault("Messages.prefix", "&f[&bGHG&6&lEssentials&f]");
		this.getConfig().addDefault("Messages.join.first", "&bWelcome to the server!");
		this.getConfig().addDefault("Messages.join.other", "&6Welcome back to the server");
		this.getConfig().addDefault("Messages.join.allow.first", true);
		this.getConfig().addDefault("Messages.join.allow.other", true);
		
		this.getConfig().options().copyDefaults(true);
		
		saveConfig();
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Join(), this);
	}

}

