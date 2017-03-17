package io.github.awesome90.crescent;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import io.github.awesome90.crescent.commands.CommandCrescent;
import io.github.awesome90.crescent.detection.CheckType;
import io.github.awesome90.crescent.listeners.DetectionListener;

public final class Crescent extends JavaPlugin {

	private static Crescent instance;

	/**
	 * @return The instance of the plugin.
	 */
	public static Crescent getInstance() {
		return instance;
	}

	private ProtocolManager protocolManager;

	@Override
	public void onEnable() {
		instance = this;

		protocolManager = ProtocolLibrary.getProtocolManager();

		// Configuration.
		loadConfig();

		registerListeners();
		registerCommands();
	}

	private void registerCommands() {
		getCommand("crescent").setExecutor(new CommandCrescent());
	}

	private void registerListeners() {
		final DetectionListener detections = new DetectionListener();

		final PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(detections, this);
	}

	/**
	 * Set the configuration defaults.
	 */
	private void loadConfig() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}

	public ProtocolManager getProtocolManager() {
		return protocolManager;
	}

}
