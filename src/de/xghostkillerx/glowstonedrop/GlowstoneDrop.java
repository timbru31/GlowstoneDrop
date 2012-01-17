package de.xghostkillerx.glowstonedrop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.*;

/**
 * GlowstoneDrop for CraftBukkit/Bukkit
 * Handles some general stuff!
 * 
 * Refer to the forum thread:
 * http://bit.ly/oW6iR1
 * Refer to the dev.bukkit.org page:
 * http://bit.ly/rcN2QB
 *
 * @author xGhOsTkiLLeRx
 * @thanks to XxFuNxX for the original GlowstoneDrop plugin!
 * 
 */

public class GlowstoneDrop extends JavaPlugin {

	public static final Logger log = Logger.getLogger("Minecraft");
	private final GlowstoneDropBlockListener blockListener = new GlowstoneDropBlockListener(this);
	public FileConfiguration config;
	public FileConfiguration localization;
	public File configFile;
	public File localizationFile;
	List<String> itemList = new ArrayList<String>();
	String[] items = {"WOOD_PICKAXE", "STONE_PICKAXE", "IRON_PICKAXE", "GOLD_PICKAXE", "DIAMOND_PICKAXE"};

	// Shutdown
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion()	+ " has been disabled!");
	}

	// Start
	public void onEnable() {
		// Events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener,	Event.Priority.Normal, this);

		// Config
		configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
		config = this.getConfig();
		loadConfig();
		
		// Localization
		localizationFile = new File(getDataFolder(), "localization.yml");
		if(!localizationFile.exists()){
	        localizationFile.getParentFile().mkdirs();
	        copy(getResource("localization.yml"), localizationFile);
	    }
		// Try to load
		try {
			localization = YamlConfiguration.loadConfiguration(localizationFile);
			loadLocalization();
		}
		// if it failed, tell it
		catch (Exception e) {
			log.warning("GlowstoneDrop failed to load the localization!");
		}

		// Message
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is enabled!");

		// Stats
		try {
			Metrics metrics = new Metrics();
			metrics.beginMeasuringPlugin(this);
		}
		catch (IOException e) {}
	}

	// Loads the config at start
	public void loadConfig() {
		config.options().header("For help please refer to http://bit.ly/oW6iR1 or http://bit.ly/rcN2QB");
		config.addDefault("configuration.permissions", true);
		config.addDefault("configuration.messages", true);
		config.addDefault("worlds.normal", "block");
		config.addDefault("worlds.nether", "dust");
		config.addDefault("worlds.end", "block");
		config.addDefault("items", Arrays.asList(items));
		itemList = config.getStringList("items");
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	// Loads the localization
	public void loadLocalization() {
		localization.options().copyDefaults(true);
		saveLocalization();
		
	}
	
	// Saves the localization
	public void saveLocalization() {
		try {
			localization.save(localizationFile);
		} catch (IOException e) {
			log.warning("CookMe failed to save the localization! Please report this!");
		}
	}
	
	// Reloads the config via command /glowstonedrop reload or /glowdrop reload
	public void loadConfigsAgain() {
		try {
			config.load(configFile);
			saveConfig();
			localization.load(localizationFile);
			saveLocalization();
			itemList = config.getStringList("items");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// If no config is found, copy the default one!
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len=in.read(buf)) >0) {
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Refer to GlowstoneDropCommands
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		GlowstoneDropCommands cmd = new GlowstoneDropCommands(this);
		return cmd.GlowstoneDropCommand(sender, command, commandLabel, args);
	}
}