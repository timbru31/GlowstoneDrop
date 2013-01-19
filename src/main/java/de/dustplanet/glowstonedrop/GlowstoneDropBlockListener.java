package de.dustplanet.glowstonedrop;

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
 * @author xGhOsTkiLLeRx
 * @thanks to XxFuNxX for the original GlowstoneDrop plugin!
 */

public class GlowstoneDropBlockListener implements Listener {
	private GlowstoneDrop plugin;
	private boolean message = true;

	public GlowstoneDropBlockListener(GlowstoneDrop instance) {
		plugin = instance;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		// Check for the item in hand and if it's on the list
		if (sameItem(player.getItemInHand().getTypeId()) && (event.getBlock().getTypeId() == 89)) {
			// Check for the config value permissions
			if (plugin.worldsBlock.contains(player.getWorld().getName().toLowerCase())) {
				// Block
				if (player.hasPermission("glowstonedrop.use." + player.getWorld()) || player.hasPermission("glowstonedrop.use.*") || !plugin.config.getBoolean("configuration.permissions")) {
					if (player.getGameMode() != GameMode.CREATIVE) dropBlock(event);
				} 
				else message(player);
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
		for (String itemName : plugin.itemList) {
			Material mat = Material.matchMaterial(itemName);
			// Invalid item
			if (mat == null) {
				// Prevent spamming
				if (message) {
					plugin.getLogger().warning("Couldn't load the items! Please check your config! The item " + itemName + " is invalid.");
					message = false;
				}
				// Go on
				continue;
			}
			// Get ID & compare
			if (mat.getId() == item) return true;
		}
		return false;
	}
}