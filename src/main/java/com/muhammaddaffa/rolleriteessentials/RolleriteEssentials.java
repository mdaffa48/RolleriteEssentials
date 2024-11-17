package com.muhammaddaffa.rolleriteessentials;

import com.muhammaddaffa.rolleriteessentials.commands.*;
import com.muhammaddaffa.rolleriteessentials.listeners.GodListener;
import com.muhammaddaffa.rolleriteessentials.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RolleriteEssentials extends JavaPlugin {

    private static RolleriteEssentials instance;

    public static Config DEFAULT_CONFIG;
    public static NamespacedKey GOD_KEY;

    @Override
    public void onEnable() {
        instance = this;
        // Initialize config
        DEFAULT_CONFIG = new Config("config.yml", null, true);
        // Initialize keys
        GOD_KEY = new NamespacedKey(this,  "god_status");
        // Register commands & listeners
        this.registerCommands();
        this.registerListeners();
    }

    private void registerCommands() {
        EnderChestCommand.register(this);
        FixCommand.register(this);
        GamemodeCommand.register(this);
        GodCommand.register(this);
        OpenInvCommand.register(this);
        TpaAcceptCommand.register(this);
        TpaCommand.register(this);
        TrashCommand.register(this);
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        // Register events
        pm.registerEvents(new GodListener(), this);
    }

    public static RolleriteEssentials getInstance() {
        return instance;
    }

}
