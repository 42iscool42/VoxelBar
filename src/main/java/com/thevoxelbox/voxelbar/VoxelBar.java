package com.thevoxelbox.voxelbar;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class VoxelBar extends JavaPlugin {
    @Override
    public void onDisable() {
        this.saveConfig();
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new VoxelBarListener(this), this);
        this.getCommand("voxelbar").setExecutor(new CommandVoxelBarExecutor(this));
    }
}
