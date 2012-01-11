package de.xghostkillerx.glowstonedrop;

import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

/**
 * GlowstoneDrop for CraftBukkit/Bukkit
 * Handles the block stuff!
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

public class GlowstoneDropBlockListener extends BlockListener {

	public static GlowstoneDrop plugin;
	public GlowstoneDropBlockListener(GlowstoneDrop instance) {
		plugin = instance;
	}
	public int message = 0;

	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		// Check for the item in hand and if it's on the list
		if (sameItem(player.getItemInHand().getTypeId()) == true
				&& (event.getBlock().getTypeId() == 89)) {
			// Check for the config value permissions
			if (plugin.config.getBoolean("configuration.permissions") == true) {
				// Normal
				if (event.getBlock().getWorld().getEnvironment().equals(Environment.NORMAL)) {
					// Block
					if (plugin.config.getString("worlds.normal").equalsIgnoreCase("block")) {
						if (player.hasPermission("glowstonedrop.use.normal")) {
							dropBlock(event);
						} else {
							message(player);
						}
					}
				}
				// Nether
				if (event.getBlock().getWorld().getEnvironment().equals(Environment.NETHER)) {
					// Block
					if (plugin.config.getString("worlds.nether").equalsIgnoreCase("block")) {
						if (player.hasPermission("glowstonedrop.use.nether")) {
							dropBlock(event);
						} else {
							message(player);
						}
					}
				}
				// The End
				if (event.getBlock().getWorld().getEnvironment().equals(Environment.THE_END)) {
					// Block
					if (plugin.config.getString("worlds.end").equalsIgnoreCase("block")) {
						if (player.hasPermission("glowstonedrop.use.end")) {
							dropBlock(event);
						} else {
							message(player);
						}
					}
				}
			}
			// Without permissions -> no messages
			else if (plugin.config.getBoolean("configuration.permissions") == false) {
				// Normal
				if (event.getBlock().getWorld().getEnvironment().equals(Environment.NORMAL)) {
					// Block
					if (plugin.config.getString("worlds.normal").equalsIgnoreCase("block")) {
						event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(89, 1));
						event.getBlock().setType(Material.AIR);
					}
				}
				// Nether
				if (event.getBlock().getWorld().getEnvironment().equals(Environment.NETHER)) {
					// Block
					if (plugin.config.getString("worlds.nether").equalsIgnoreCase("block")){
						dropBlock(event);
					}
				}
				// The End
				if (event.getBlock().getWorld().getEnvironment().equals(Environment.THE_END)) {
					// Block
					if (plugin.config.getString("worlds.end").equalsIgnoreCase("block")) {
						dropBlock(event);
					}
				}
			}
		}
	}

	// Drop the block
	public void dropBlock(BlockBreakEvent event) {
		event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(89, 1));
		event.getBlock().setType(Material.AIR);
	}

	// Sends a message
	public void message(Player player) {
		if (plugin.config.getBoolean("configuration.messages") == true) {
			player.sendMessage(ChatColor.DARK_RED + "You don't have the permission to use GlowstoneDrop! Dropping dust instead!");
		}
	}

	// Is the item in the hand on the list?
	private boolean sameItem(int item) {
		for (int i = 0; i < plugin.itemList.size(); i++) {
			String itemName = plugin.itemList.get(i);
			try {
				Material material = Material.valueOf(itemName);
				if (material.getId() == item) {
					return true;
				}
			}
			catch (Exception e) {
				// Prevent spamming
				if (message == 0) {
					GlowstoneDrop.log.warning("GlowstoneDrop couldn't load the items! Please check your config!");
					message = 1;
				}
			}
		}
		return false;
	}
}