This is the README of GlowstoneDrop
Thanks to XxFuNxX for the original plugin!
Thanks for using!
For support visit the old forum thread: http://bit.ly/oW6iR1
or the new dev.bukkit.org page: http://bit.ly/rcN2QB

This plugin sends usage statistics! If you wish to disable the usage stats, look at /plugins/PluginMetrics/config.yml!
This plugin is released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported (CC BY-NC-SA 3.0) license.


Standard config:

# For help please refer to http://bit.ly/oW6iR1 or http://bit.ly/rcN2QB
configuration:
  permissions: true
  messages: true
items:
- WOOD_PICKAXE
- STONE_PICKAXE
- IRON_PICKAXE
- GOLD_PICKAXE
- DIAMOND_PICKAXE

Commands & Permissions (if no permissions system is detected, only OPs are able to use the commands!)
Only bukkit's permissions system is supported! If a command is typed wrong, 
it will return, that you should type /glowstonedrop help or /glowdrop help for more information!

glowstonedrop.use.<worldName>
Description: Needed to use the plugin in the specific world! (Otherwise you get dust!)

/glowstonedrop reload
/glowdrop reload
Node: glowstonedrop.reload
Description: Reloads the config

/glowstonedrop help
/glowdrop help
Node: glowstonedrop.help
Description: Displays the help

/glowstonedrop enable permissions
/glowdrop enable permissions
Node: glowstonedrop.enable.permissions
Description: Enables the permissions! (Only OPs or player with the permission can use the plugin!)

/glowstonedrop disable permissions
/glowdrop disable permissions
Node: glowstonedrop.disable.permissions
Description: Disables the permissions! ALL players can use the plugin!

/glowstonedrop enable messages
/glowdrop enable messages
Node: glowstonedrop.enable.messages
Description: Enables the messages

/glowstonedrop disable messages
/glowdrop disable messages
Node: glowstonedrop.disable.messages
Description: Disables the messages

/glowstonedrop set normal dust
/glowdrop set normal dust
Node: glowstonedrop.set.normal
Description: Changes the drop in normal worlds to dust

/glowstonedrop set <world> <block|dust>
/glowdrop set <world> <block|dust>
Node: glowstonedrop.set
Description: Changes the drop in world