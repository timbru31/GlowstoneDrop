package de.dustplanet.glowstonedrop;

import java.util.Arrays;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

/**
 * GlowstoneDrop for CraftBukkit/Bukkit
 * Handles the block stuff!
 * Refer to the forum thread:
 * http://bit.ly/oW6iR1
 * Refer to the dev.bukkit.org page:
 * http://bit.ly/rcN2QB
 * 
 * @author  xGhOsTkiLLeRx
 * @thanks  to XxFuNxX for the original GlowstoneDrop plugin!
 */

public class GlowstoneDropBlockListener implements Listener {
	private GlowstoneDrop plugin;
	private boolean message = true;
	private String[] worlds = {"normal", "nether", "end"};
	public GlowstoneDropBlockListener(GlowstoneDrop instance) {
		plugin = instance;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		String enviroment = event.getBlock().getWorld().getEnvironment().toString().toLowerCase();
		// Check for the item in hand and if it's on the list
		if (sameItem(player.getItemInHand().getTypeId()) && (event.getBlock().getTypeId() == 89)) {
			// Check for the config value permissions
			if (Arrays.asList(worlds).contains(enviroment)) {
				// Block
				if (plugin.config.getString("worlds." + enviroment).equalsIgnoreCase("block")) {
					if (player.hasPermission("glowstonedrop.use." + enviroment) || !plugin.config.getBoolean("configuration.permissions")) {
						if (player.getGameMode() != GameMode.CREATIVE) dropBlock(event);
					} 
					else message(player);
				}
			}
		}
	}

	// Drop the block
	private void dropBlock(BlockBreakEvent event) {
		event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(89, 1));
		event.getBlock().setType(Material.AIR);
	}

	// Sends a message
	private void message(Player player) {
		if (plugin.config.getBoolean("configuration.messages")) {
			String msg = plugin.localization.getString("permission_denied");
			plugin.message(null, player, msg, null, null);
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
				if (message) {
					plugin.log.warning("[GlowstoneDrop] Couldn't load the items! Please check your config! The item " + itemName + " is invalid.");
					message = false;
				}
			}
		}
		return false;
	}
}