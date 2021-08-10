package org.bgmsound.customprefix;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class CustomPrefix extends JavaPlugin {
    public static HashMap<Player, Prefix> titlemap = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        PrefixInteract interact = new PrefixInteract();
        PrefixGUI pg = new PrefixGUI();
        getCommand("커스텀칭호").setExecutor(interact);
        getCommand("칭호").setExecutor(pg);
        getServer().getPluginManager().registerEvents(interact, this);
        getServer().getPluginManager().registerEvents(pg, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
