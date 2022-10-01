package net.rebix.engine;

import net.rebix.engine.utils.Registry;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class JavaEngine extends JavaPlugin {
    public static Integer INTEGER_LIMIT = 2147483647;
    public static String Language = "English";

    public static Plugin plugin;

    public static String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public void onEnable() {

        plugin = this;
        //if(!new File(this.getDataFolder(),"config.yml").exists()) System.out.println("[RebixEngine] Config file not found, creating new one");

        //Removing unwanted Entity's with scoreboard Tag: RemoveEntityOnDisable
        for (World world : Bukkit.getWorlds())
            for (Entity entity : world.getEntities())
                if (entity.getScoreboardTags().contains("RemoveEntityOnDisable")) entity.remove();
        new Registry();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
