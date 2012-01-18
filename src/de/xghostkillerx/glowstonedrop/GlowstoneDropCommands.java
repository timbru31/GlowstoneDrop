package de.xghostkillerx.glowstonedrop;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * GlowstoneDrop for CraftBukkit/Bukkit
 * Handles the commands!
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

public class GlowstoneDropCommands {

	GlowstoneDrop plugin;
	public GlowstoneDropCommands(GlowstoneDrop instance) {
		plugin = instance;
	}
	String[] worlds = {"normal", "nether", "end"}, values = {"block", "dust"};
	String message;
	int i;

	// Commands; always check for permissions!
	public boolean GlowstoneDropCommand (CommandSender sender, Command command, String commandLabel, String[] args) {
		if ((command.getName().equalsIgnoreCase("glowstonedrop")) || (command.getName().equalsIgnoreCase("glowdrop"))) {
			// reload
			if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("glowstonedrop.reload")) {
						GlowstoneDropReload(sender, args);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						message(sender, message);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					GlowstoneDropReload(sender, args);
					return true;
				}
			}
			// help
			if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
				if (plugin.config.getBoolean("configuration.permissions") == true) {
					if (sender.hasPermission("glowstonedrop.help")) {
						GlowstoneDropHelp(sender, args);
						return true;
					}
					else {
						message = plugin.localization.getString("permission_denied");
						message(sender, message);
						return true;
					}
				}
				if (plugin.config.getBoolean("configuration.permissions") == false) {
					GlowstoneDropHelp(sender, args);
					return true;
				}
			}
			// set
			if (args.length > 2 && args[0].equalsIgnoreCase("set")) {
				String world = args[1], value = args[2];
				if (Arrays.asList(worlds).contains(args[1])) {
					if (Arrays.asList(values).contains(args[2])) {
						if (plugin.config.getBoolean("configuration.permissions") == true) {
							if (sender.hasPermission("glowstonedrop.set.normal")) {
								GlowstoneDropSet(sender, args, world, value);
							}
							else {
								message = plugin.localization.getString("permission_denied");
								message(sender, message);
								return true;
							}
						}
						if (plugin.config.getBoolean("configuration.permissions") == false) {
							GlowstoneDropSet(sender, args, world, value);
							return true;
						}
					}
				}
				// all
				if (args.length > 2 && args[1].equalsIgnoreCase("all")) {
					// block
					if (Arrays.asList(values).contains(args[2])) {
						if (plugin.config.getBoolean("configuration.permissions") == true) {
							if (sender.hasPermission("glowstonedrop.set.all")) {
								GlowstoneDropSetAll(sender, args, value);
								return true;
							}
							else {
								message = plugin.localization.getString("permission_denied");
								message(sender, message);
								return true;
							}
						}
						if (plugin.config.getBoolean("configuration.permissions") == false) {
							GlowstoneDropSetAll(sender, args, value);
							return true;
						}
					}
				}
			}
			// enable
			if (args.length > 0 && args[0].equalsIgnoreCase("enable")) {
				// permissions
				if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
					if (plugin.config.getBoolean("configuration.permissions") == true) {
						if (sender.hasPermission("glowstonedrop.enable.permissions")) {
							GlowstoneDropEnablePermissions(sender, args);
							return true;
						}
						else {
							message = plugin.localization.getString("permission_denied");
							message(sender, message);
							return true;
						}
					}
					if (plugin.config.getBoolean("configuration.permissions") == false) {
						GlowstoneDropEnablePermissions(sender, args);
						return true;
					}
				}
				// messages
				if (args.length > 1 && args[1].equalsIgnoreCase("messages")) {
					if (plugin.config.getBoolean("configuration.permissions") == true) {
						if (sender.hasPermission("glowstonedrop.enable.messages")) {
							GlowstoneDropEnableMessages(sender, args);
							return true;
						}
						else {
							message = plugin.localization.getString("permission_denied");
							message(sender, message);
							return true;
						}
					}
					if (plugin.config.getBoolean("configuration.permissions") == false) {
						GlowstoneDropEnableMessages(sender, args);
						return true;
					}
				}
			}
			// disable
			if (args.length > 0 && args[0].equalsIgnoreCase("disable")) {
				// permissions
				if (args.length > 1 && args[1].equalsIgnoreCase("permissions")) {
					if (plugin.config.getBoolean("configuration.permissions") == true) {
						if (sender.hasPermission("glowstonedrop.disable.permissions")) {
							GlowstoneDropDisablePermissions(sender, args);
							return true;
						} else {
							message = plugin.localization.getString("permission_denied");
							message(sender, message);
							return true;
						}
					}
					if (plugin.config.getBoolean("configuration.permissions") == false) {
						GlowstoneDropDisablePermissions(sender, args);
						return true;
					}
				}
				// messages
				if (args.length > 1 && args[1].equalsIgnoreCase("messages")) {
					if (plugin.config.getBoolean("configuration.permissions") == true) {
						if (sender.hasPermission("glowstonedrop.disable.messages")) {
							GlowstoneDropDisableMessages(sender, args);
							return true;
						} else {
							message = plugin.localization.getString("permission_denied");
							message(sender, message);
							return true;
						}
					}
					if (plugin.config.getBoolean("configuration.permissions") == false) {
						GlowstoneDropDisableMessages(sender, args);
						return true;
					}
				}
			}
		}
		return false;
	}

	private void message(CommandSender sender, String message2) {
		PluginDescriptionFile pdfFile = plugin.getDescription();
		sender.sendMessage(message
				.replaceAll("&([0-9a-f])", "\u00A7$1")
				.replaceAll("%version", pdfFile.getVersion()));
	}

	private void message(CommandSender sender, String world, String value, String message) {
		sender.sendMessage(message
				.replaceAll("&([0-9a-f])", "\u00A7$1")
				.replaceAll("%world", world)
				.replaceAll("%value", value));
	}
	

	private void message(CommandSender sender, String message, String value) {
		sender.sendMessage(message
				.replaceAll("&([0-9a-f])", "\u00A7$1")
				.replaceAll("%value", value));
	}

	private void GlowstoneDropSet(CommandSender sender, String[] args, String world, String value) {
		plugin.config.set("worlds." + world.toLowerCase(), value.toLowerCase());
		plugin.saveConfig();
		message = plugin.localization.getString("set");
		message(sender, world, value, message);

	}

	// See the help with /glowstonedrop help or /glowdrop help
	private boolean GlowstoneDropHelp(CommandSender sender, String[] args) {
		for (i=1; i <= 12; i++) {
			message = plugin.localization.getString("help_" + Integer.toString(i));
			message(sender, message);
		}
		return true;
	}

	// Reloads the config with /glowstonedrop reload or /glowdrop reload
	private boolean GlowstoneDropReload(CommandSender sender, String[] args) {
		plugin.loadConfigsAgain();
		message = plugin.localization.getString("reload");
		message(sender, message);
		return true;
	}

	// Enables permissions with /glowstonedrop enable permissions or /glowdrop enable permissions
	private boolean GlowstoneDropEnablePermissions(CommandSender sender, String[] args) {
		plugin.config.set("configuration.permissions", true);
		plugin.saveConfig();
		for (i=1; i <= 2; i++) {
			message = plugin.localization.getString("enable_permissions_" + Integer.toString(i));
			message(sender, message);
		}
		return true;
	}

	// Disables permissions with /glowstonedrop disable permissions or /glowdrop disable permissions
	private boolean GlowstoneDropDisablePermissions(CommandSender sender, String[] args) {
		plugin.config.set("configuration.permissions", false);
		plugin.saveConfig();
		for (i=1; i <= 2; i++) {
			message = plugin.localization.getString("disable_permissions_" + Integer.toString(i));
			message(sender, message);
		}
		return true;
	}

	// Enables messages with /glowstonedrop enable messages or /glowdrop enable messages
	private boolean GlowstoneDropEnableMessages(CommandSender sender, String[] args) {
		plugin.config.set("configuration.messages", true);
		plugin.saveConfig();
		message = plugin.localization.getString("enable_messages");
		message(sender, message);
		return true;
	}

	// Disables messages with /glowstonedrop disable messages or /glowdrop disable messages
	private boolean GlowstoneDropDisableMessages(CommandSender sender, String[] args) {
		plugin.config.set("configuration.messages", false);
		plugin.saveConfig();
		message = plugin.localization.getString("disable_messages");
		message(sender, message);
		return true;
	}


	// Sets the all drops to dust with /glowstonedrop set all dust or /glowdrop set all dust
	private boolean GlowstoneDropSetAll(CommandSender sender, String[] args, String value) {
		plugin.config.set("worlds.normal", value.toLowerCase());
		plugin.config.set("worlds.nether", value.toLowerCase());
		plugin.config.set("worlds.end", value.toLowerCase());
		plugin.saveConfig();
		message = plugin.localization.getString("set_all");
		message(sender, message, value);
		return true;
	}
}
