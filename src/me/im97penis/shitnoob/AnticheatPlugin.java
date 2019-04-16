package me.im97penis.shitnoob;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.im97penis.shitnoob.check.CheckManager;
import me.im97penis.shitnoob.internal.ListenerHandles;
import me.im97penis.shitnoob.profile.ProfileManager;

@Getter
public class AnticheatPlugin extends JavaPlugin {

	@Getter
	private static AnticheatPlugin plugin;
	
	private CheckManager checkManager;
	private ProfileManager profileManager;
	
	@Override
	public void onLoad() {
		
	}
	
	@Override
	public void onEnable() {
		/*
		 * TODO: database if needed
		 * 
		 */
		
		this.saveDefaultConfig();
		this.loadManagers();
		this.loadListeners();
		this.loadCommands();
		
		AnticheatPlugin.plugin = this;
	}
	
	private void loadManagers() {
		this.checkManager = new CheckManager();
		this.profileManager = new ProfileManager();
	}
	
	private void loadListeners() {
		this.getServer().getPluginManager().registerEvents(new ListenerHandles(this), this);
	}
	
	private void loadCommands() {
		
	}
	
	@Override
	public void onDisable() {
		
		
		AnticheatPlugin.plugin = null;
	}
	
	
}
