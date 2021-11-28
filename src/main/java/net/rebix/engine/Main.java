package net.rebix.engine;

import net.rebix.engine.util.Registry;
import net.rebix.engine.util.api.ScrollableInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Integer INTEGER_LIMIT = 2147483647;

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        new Registry();
        for(Player player: Bukkit.getOnlinePlayers()){
            ScrollableInventory sbinventory = new ScrollableInventory().create(player,"test",6*9,null,null);
            sbinventory.reloadInventory();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
