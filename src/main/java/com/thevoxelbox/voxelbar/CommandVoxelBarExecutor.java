package com.thevoxelbox.voxelbar;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class CommandVoxelBarExecutor implements CommandExecutor {
    private static final String HELP_TEXT = ChatColor.GOLD + "[VoxelBar Help]\n" +
            ChatColor.GREEN + "/vbar\n" +
            ChatColor.WHITE + "    - Shows this help message\n\n" +
            ChatColor.GREEN + "/vbar [on|off]\n" +
            ChatColor.WHITE + "    - Enables/Disables scrolling by crouching\n\n" +
            ChatColor.GREEN + "/vbar scroll [+|-]\n" +
            ChatColor.WHITE + "    - Scroll your inventory up/down";
    private static final String ENABLED_MESSAGE = ChatColor.GREEN + "VoxelBar enabled for you - " +
            ChatColor.DARK_AQUA + "/%s off" + ChatColor.GREEN + " to turn it off again!";
    private static final String DISABLED_MESSAGE = ChatColor.GREEN + "VoxelBar disabled for you - " +
            ChatColor.DARK_AQUA + "/%s on" + ChatColor.GREEN + " to turn it on again!";
    private static final String SCROLL_UP_MESSAGE = ChatColor.GREEN + "You moved your inventory " +
            ChatColor.DARK_AQUA + "upwards";
    private static final String SCROLL_DOWN_MESSAGE = ChatColor.GREEN + "You moved your inventory " +
            ChatColor.GOLD + "downwards";

    private final Plugin plugin;

    public CommandVoxelBarExecutor(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You cannot use VoxelBar from the console!");
            return true;
        }

        Player player = (Player) commandSender;

        if (args == null || args.length == 0) {
            player.sendMessage(HELP_TEXT);
            return true;
        }

        String argument = args[0];

        if (argument.equalsIgnoreCase("enable") ||
                argument.equalsIgnoreCase("on") ||
                argument.equalsIgnoreCase("e")) {

            VoxelBarFunctions.setScrollingEnabled(plugin, player, true);
            player.sendMessage(String.format(ENABLED_MESSAGE, label));
            return true;
        } else if (argument.equalsIgnoreCase("disable") ||
                argument.equalsIgnoreCase("off") ||
                argument.equalsIgnoreCase("d")) {
            VoxelBarFunctions.setScrollingEnabled(plugin, player, false);
            player.sendMessage(String.format(DISABLED_MESSAGE, label));
            return true;
        } else if (argument.equalsIgnoreCase("scroll") || argument.equalsIgnoreCase("s")) {
            if (args.length == 1 || args[1].equals("+")) {
                VoxelBarFunctions.moveInventory(player, 1);
                player.sendMessage(SCROLL_UP_MESSAGE);
            } else if (args[1].equals("-")) {
                VoxelBarFunctions.moveInventory(player, -1);
                player.sendMessage(SCROLL_DOWN_MESSAGE);
            } else {
                player.sendMessage(ChatColor.GREEN + "Unknown scroll direction.");
            }
            return true;
        }

        player.sendMessage(HELP_TEXT);
        return true;
    }


}

