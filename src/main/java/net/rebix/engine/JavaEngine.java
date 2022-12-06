package net.rebix.engine;

import net.rebix.engine.utils.Database;
import net.rebix.engine.utils.Registry;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class JavaEngine extends JavaPlugin {
    public static Integer INTEGER_LIMIT = 2147483647;
    public static String Language = "English";
    public static Database DATABASE;

    public static Plugin plugin;

    public static String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public void onEnable() {

        if (getConfig().getString("servertype") == null) {
            getConfig().set("servertype", "test_servers");

            getConfig().set("dbhost", "host");
            getConfig().set("dbport", "3306");
            getConfig().set("dbdatabase", "JavaEngine");
            getConfig().set("dbusername", "root");
            getConfig().set("dbpassword", "root");
            System.err.println("Config created! Please edit the config.yml before continuing!");
            saveConfig();
            try {
                Bukkit.getPluginManager().disablePlugin(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DATABASE = new Database(getConfig().getString("dbhost"), getConfig().getString("dbport"), getConfig().getString("dbdatabase"), getConfig().getString("dbusername"), getConfig().getString("dbpassword"));

        plugin = this;
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
