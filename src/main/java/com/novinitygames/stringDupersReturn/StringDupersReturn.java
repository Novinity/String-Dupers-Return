package com.novinitygames.stringDupersReturn;

import com.novinitygames.stringDupersReturn.listener.BlockListeners;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class StringDupersReturn extends JavaPlugin {

    private static StringDupersReturn instance;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new BlockListeners(), this);

        // bStats
        int pluginId = 29724;
        Metrics metrics = new Metrics(this, pluginId);
    }

    public static StringDupersReturn getInstance() {return instance;}
}
