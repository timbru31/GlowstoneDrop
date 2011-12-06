This is the README of GlowstoneDrop
Thanks to XxFuNxX for the original plugin!
Thanks for using!
For support visit the old forum thread: http://bit.ly/oW6iR1
or the new dev.bukkit.org page: http://bit.ly/rcN2QB

This plugin sends usage statistics! If you wish to disable the usage stats, look at /plugins/PluginStats/config.yml!
This plugin is released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported (CC BY-NC-SA 3.0)


Standard config:

For the custom item put the number in the ''! For more than one item, sepreate them via a comma! If you use more than one custom item, you can delete the '',
but PLEASE use them again, if you only use one item again!

# For help please refer to http://bit.ly/oW6iR1 or http://bit.ly/rcN2QB
configuration:
  permissions: true
  messages: true
worlds:
  normal: block
  nether: dust
  end: block
items: ''

Commands & Permissions (if no permissions system is detected, only OPs are able to use the commands!)
Only bukkit's permissions system is supported! If a command is typed wrong, 
it will return, that you should type /glowstonedrop help or /glowdrop help for more information!

glowstonedrop.use.normal
Description: Needed to use the plugin in the normal worlds! (Otherwise you get dust!)

glowstonedrop.use.nether
Description: Needed to use the plugin in the nether worlds! (Otherwise you get dust!)

glowstonedrop.use.end
Description: Needed to use the plugin in the skland worlds! (Otherwise you get dust!)

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

/glowstonedrop set normal block
/glowdrop set normal block
Node: glowstonedrop.set.normal
Description: Changes the drop in normal worlds to blocks

/glowstonedrop set nether dust
/glowdrop set nether dust
Node: glowstonedrop.set.nether
Description: Changes the drop in nether worlds to dust

/glowstonedrop set nether block
/glowdrop set nether block
Node: glowstonedrop.set.nether
Description: Changes the drop in nether worlds to blocks

/glowstonedrop set end dust
/glowdrop set end dust
Node: glowstonedrop.set.end
Description: Changes the drop in end worlds to dust

/glowstonedrop set end block
/glowdrop set end block
Node: glowstonedrop.set.end
Description: Changes the drop in end worlds to blocks

/glowstonedrop set all dust
/glowdrop set all dust
Node: glowstonedrop.set.all
Description: Changes the drop in all worlds to dust

/glowstonedrop set all block
/glowdrop set all block
Node: glowstonedrop.set.all
Description: Changes the drop in all worlds to blocks