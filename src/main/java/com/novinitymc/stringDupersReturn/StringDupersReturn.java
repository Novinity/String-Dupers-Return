package com.novinitymc.stringDupersReturn;

import com.novinitymc.stringDupersReturn.listener.BlockListeners;
import org.bukkit.plugin.java.JavaPlugin;

public final class StringDupersReturn extends JavaPlugin {

    private static StringDupersReturn instance;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new BlockListeners(), this);
    }

    public static StringDupersReturn getInstance() {return instance;}
}
