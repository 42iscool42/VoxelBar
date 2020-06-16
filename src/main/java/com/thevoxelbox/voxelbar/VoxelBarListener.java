package com.thevoxelbox.voxelbar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.Plugin;

public class VoxelBarListener implements Listener {
    private final Plugin plugin;

    public VoxelBarListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        if (player.isSneaking()) {
            if ((player.isOp() || player.hasPermission("voxelbar.use")) &&
                    VoxelBarFunctions.isScrollingEnabled(plugin, player)) {
                int scrollDelta = VoxelBarFunctions.getDelta(event.getPreviousSlot(), event.getNewSlot());

                if (scrollDelta == 1 || scrollDelta == -1) {
                    VoxelBarFunctions.moveInventory(player, scrollDelta);
                }

            }
        }
    }
}
