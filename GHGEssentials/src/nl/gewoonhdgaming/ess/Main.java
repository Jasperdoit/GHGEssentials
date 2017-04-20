package nl.gewoonhdgaming.ess;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.gewoonhdgaming.ess.commands.AdminMode;
import nl.gewoonhdgaming.ess.commands.ChatAdmin;
import nl.gewoonhdgaming.ess.commands.essentials.Nick;
import nl.gewoonhdgaming.ess.commands.essentials.Teleport;
import nl.gewoonhdgaming.ess.files.PlayerData;
import nl.gewoonhdgaming.ess.listeners.Join;
import nl.gewoonhdgaming.ess.listeners.adminmode.CompassClick;
import nl.gewoonhdgaming.ess.listeners.chatadmin.ChatEvent;

public class Main extends JavaPlugin {
	
	static Main pl;
	
	private PlayerData playerData;
	
	@Override
	public void onEnable() {
		pl = this;
		this.playerData = new PlayerData(this);
		setupFileSystem();
		registerEvents();
		loadConfig();
		registerCommands();
		
	}
	
	private void registerCommands() {
		getCommand("chatAdmin").setExecutor(new ChatAdmin());
		getCommand("AdminMode").setExecutor(new AdminMode());
		//ESSENTIALS COMMANDS
		getCommand("teleport").setExecutor(new Teleport());
		getCommand("nick").setExecutor(new Nick());
	}

	@Override
	public void onDisable() {
		saveConfig();
		playerData.save();
	}
	
	public static Main getPlugin() {
		return pl;
	}
	
	public void setupFileSystem() {
		if(!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
	}
	
	public void loadConfig() {
		//Messages
		this.getConfig().addDefault("Messages.prefix", "&f[&bGHG&6&lEssentials&f]");
		this.getConfig().addDefault("Messages.join.first", "&bWelcome to the server!");
		this.getConfig().addDefault("Messages.join.other", "&6Welcome back to the server");
		this.getConfig().addDefault("Messages.join.allow.first", true);
		this.getConfig().addDefault("Messages.join.allow.other", true);
		this.getConfig().addDefault("Messages.commands.permissiondeny", "&4Je mag dat commando niet uitvoeren!");
		this.getConfig().addDefault("Messages.chatAdmin.cooldown", "Je moet nog %cooldown% secondes wachten!");
		
		//ChatManager
		this.getConfig().addDefault("chatAdmin.cooldown", 5);
		
		//Permissions
		this.getConfig().addDefault("Permissions.chatAdmin.admin", "ghge.chatadmin.admin");
		this.getConfig().addDefault("Permissions.adminmode.admin", "ghge.adminmode.admin");
		this.getConfig().addDefault("Permissions.commands.tp", "ghge.commands.tp");
		this.getConfig().addDefault("Permissions.commands.tp_naar_iemand_anders", "ghge.commands.tp.other");
		this.getConfig().addDefault("Permissions.commands.nick", "ghge.commands.nick.other");

		this.getConfig().options().copyDefaults(true);
		
		//PLAYERDATA
		saveConfig();
		playerData.save();
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Join(), this);
		pm.registerEvents(new ChatEvent(), this);
		pm.registerEvents(new CompassClick(), this);
	}

}

