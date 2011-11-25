package de.xghostkillerx.glowstonedrop;

import java.util.ArrayList;
import org.bukkit.Material;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
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
import com.randomappdev.pluginstats.Ping;

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
	public File configFile;
	final List<Integer> items = new ArrayList<Integer>();

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
		if(!configFile.exists()){
	        configFile.getParentFile().mkdirs();
	        copy(getResource("config.yml"), configFile);
	    }
		config = this.getConfig();
		loadConfig();
		
		// Message
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is enabled!");
		
		// Stats
		Ping.init(this);
	}
	
	// Loads the config at start
	public void loadConfig() {
		config.options().header("For help please refer to http://bit.ly/oW6iR1 or http://bit.ly/rcN2QB");
		config.addDefault("configuration.permissions", true);
		config.addDefault("configuration.messages", true);
		config.addDefault("worlds.normal", "block");
		config.addDefault("worlds.nether", "dust");
		config.addDefault("worlds.end", "block");
		items();
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	// Reloads the config via command /glowstonedrop reload or /glowdrop reload
	public void loadConfigAgain() {
		try {
			config.load(configFile);
			saveConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// If no config is found, copy the default one!
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while((len=in.read(buf))>0){
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
	
	public List<Integer> items()
	{
		for (String itemName : config.getString("configuration.items", "").split(","))
		{
			itemName = itemName.trim();
			if (itemName.isEmpty())
			{
				continue;
			}
			Material is;
			try
			{
				//is = ess.getItemDb().get(itemName);
				is = Material.getMaterial(itemName);
				items.add(is.getId());
			}
			catch (Exception ex)
			{
				PluginDescriptionFile pdfFile = this.getDescription();
				log.info(pdfFile.getName() + " failed to load items!");
			}
		}
		return items;
	}
}
