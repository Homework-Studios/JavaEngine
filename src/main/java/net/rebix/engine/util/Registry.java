package net.rebix.engine.util;

import net.rebix.engine.Main;
import net.rebix.engine.util.api.ScrollableInventory;
import net.rebix.engine.util.events.onInventoryClickEvent;
import org.bukkit.Bukkit;

public class Registry {
    public Registry(){
        Bukkit.getPluginManager().registerEvents(new onInventoryClickEvent(), Main.plugin);
        Bukkit.getPluginManager().registerEvents(new ScrollableInventory(), Main.plugin);
    }
}
