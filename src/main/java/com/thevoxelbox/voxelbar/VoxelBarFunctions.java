package com.thevoxelbox.voxelbar;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VoxelBarFunctions {
    public static final ItemStack[] EMPTY_ITEMSTACK_ARRAY = new ItemStack[0];
    private static final int HOTBAR_SIZE = 9;
    private static String NAMESPACE_KEY = "scrolling";

    public static void moveInventory(Player player, int direction) {
        List<ItemStack> inventory = new ArrayList<>(Arrays.asList(player.getInventory().getStorageContents()));

        Collections.rotate(inventory, direction * HOTBAR_SIZE);

        player.getInventory().setStorageContents(inventory.toArray(EMPTY_ITEMSTACK_ARRAY));
    }

    public static int getDelta(int previousIndex, int newIndex) {
        final int nonOverlapping = newIndex - previousIndex;
        final int overlapping = newIndex - HOTBAR_SIZE - previousIndex;
        return Math.abs(nonOverlapping) <= Math.abs(overlapping) ? nonOverlapping : overlapping;
    }

    public static void setScrollingEnabled(Plugin plugin, Player player, boolean enabled) {
        byte val = (byte) (enabled ? 1 : 0);
        NamespacedKey key = new NamespacedKey(plugin, NAMESPACE_KEY);
        player.getPersistentDataContainer().set(key, PersistentDataType.BYTE, val);
    }

    public static boolean isScrollingEnabled(Plugin plugin, Player player) {
        NamespacedKey key = new NamespacedKey(plugin, NAMESPACE_KEY);
        return player.getPersistentDataContainer().getOrDefault(key, PersistentDataType.BYTE, (byte) 0) == 1;
    }
}
