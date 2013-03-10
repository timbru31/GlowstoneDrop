package de.dustplanet.glowstonedrop;

import java.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * GlowstoneDrop for CraftBukkit/Bukkit
 * Handles the commands!
 * 
 * Refer to the forum thread:
 * http://bit.ly/oW6iR1
 * 
 * Refer to the dev.bukkit.org page:
 * http://bit.ly/rcN2QB
 * 
 * @author xGhOsTkiLLeRx
 * thanks to XxFuNxX for the original GlowstoneDrop plugin!
 */

public class GlowstoneDropCommands implements CommandExecutor {
    private GlowstoneDrop plugin;
    private String[] values = {"block", "dust"};

    public GlowstoneDropCommands(GlowstoneDrop instance) {
	plugin = instance;
    }

    // Commands, always check for permissions!
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	// reload
	String message = "";
	if (args.length == 0) {
	    return false;
	}
	if (args[0].equalsIgnoreCase("reload")) {
	    if (sender.hasPermission("glowstonedrop.reload") || !plugin.config.getBoolean("configuration.permissions")) {
		glowstoneDropReload(sender);
	    } else {
		message = plugin.localization.getString("permission_denied");
		plugin.message(sender, null, message, null, null);
	    }
	    return true;
	}
	// help
	else if (args[0].equalsIgnoreCase("help")) {
	    if (sender.hasPermission("glowstonedrop.help") || !plugin.config.getBoolean("configuration.permissions")) {
		glowstoneDropHelp(sender);
	    } else {
		message = plugin.localization.getString("permission_denied");
		plugin.message(sender, null, message, null, null);
	    }
	    return true;
	}
	// set
	else if (args[0].equalsIgnoreCase("set")) {
	    // Usage set <world> <drop>
	    if (args.length < 3) {
		return false;
	    }
	    String world = args[1], value = args[2];
	    if (Arrays.asList(values).contains(args[2])) {
		if (sender.hasPermission("glowstonedrop.set") || !plugin.config.getBoolean("configuration.permissions")) {
		    glowstoneDropSet(sender, world, value);
		} else {
		    message = plugin.localization.getString("permission_denied");
		    plugin.message(sender, null, message, null, null);
		}
		return true;
	    }
	}
	// Usage: enable/disable perm/msg
	if (args.length < 2) {
	    return false;
	}
	// enable
	if (args[0].equalsIgnoreCase("enable") && (args[1].equalsIgnoreCase("messages") || args[1].equalsIgnoreCase("permissions"))) {
	    // permissions
	    String value = args[1];
	    if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
		if (sender.hasPermission("glowstonedrop.enable." + args[0]) || !plugin.config.getBoolean("configuration.permissions")) {
		    glowstoneDropEnable(sender, value);
		} else {
		    message = plugin.localization.getString("permission_denied");
		    plugin.message(sender, null, message, null, null);
		}
		return true;
	    }
	}
	// disable
	else if (args[0].equalsIgnoreCase("disable") && (args[1].equalsIgnoreCase("messages") || args[1].equalsIgnoreCase("permissions"))) {
	    // permissions
	    String value = args[1];
	    if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
		if (sender.hasPermission("glowstonedrop.disable." + args[0]) || !plugin.config.getBoolean("configuration.permissions")) {
		    glowstoneDropDisable(sender, value);
		} else {
		    message = plugin.localization.getString("permission_denied");
		    plugin.message(sender, null, message, null, null);
		}
		return true;
	    }
	}
	return false;
    }

    // Set a value
    private void glowstoneDropSet(CommandSender sender, String world, String value) {
	world = world.toLowerCase();
	if (value.equalsIgnoreCase("block") && !plugin.worldsBlock.contains(world)) {
	    plugin.worldsBlock.add(world);
	} else if (value.equalsIgnoreCase("dust")) {
	    plugin.worldsBlock.remove(world);
	}
	plugin.config.set("worldsBlock", plugin.worldsBlock);
	plugin.saveConfig();
	String message = plugin.localization.getString("set");
	plugin.message(sender, null, message, value, world);
    }

    // See the help with /glowstonedrop help or /glowdrop help
    private void glowstoneDropHelp(CommandSender sender) {
	for (int i = 1; i <= 11; i++) {
	    String message = plugin.localization.getString("help_" + Integer.toString(i));
	    plugin.message(sender, null, message, null, null);
	}
    }

    // Reloads the config with /glowstonedrop reload or /glowdrop reload
    private void glowstoneDropReload(CommandSender sender) {
	plugin.loadConfigsAgain();
	String message = plugin.localization.getString("reload");
	plugin.message(sender, null, message, null, null);
    }

    // Enables permissions with /glowstonedrop enable <value> or /glowdrop
    // enable <value>
    private void glowstoneDropEnable(CommandSender sender, String value) {
	plugin.config.set("configuration." + value, true);
	plugin.saveConfig();
	String message = "";
	if (value.equalsIgnoreCase("permissions")) {
	    for (int i = 1; i <= 2; i++) {
		message = plugin.localization.getString("enable_permissions_" + Integer.toString(i));
		plugin.message(sender, null, message, null, null);
	    }
	} else {
	    message = plugin.localization.getString("enable_messages");
	    plugin.message(sender, null, message, null, null);
	}
    }

    // Disables messages with /glowstonedrop disable <value> or /glowdrop
    // disable <value>
    private void glowstoneDropDisable(CommandSender sender, String value) {
	plugin.config.set("configuration." + value, false);
	plugin.saveConfig();
	String message = "";
	if (value.equalsIgnoreCase("permissions")) {
	    for (int i = 1; i <= 2; i++) {
		message = plugin.localization.getString("disable_permissions_" + Integer.toString(i));
		plugin.message(sender, null, message, null, null);
	    }
	} else {
	    message = plugin.localization.getString("disable_messages");
	    plugin.message(sender, null, message, null, null);
	}
    }
}