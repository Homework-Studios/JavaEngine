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

    public static Plugin plugin;

    public static String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public void onEnable() {

        Database database = new Database("localhost", "3306", "javaengine", "root", "tJyqifRvCamHJQlFIeny");
        //System.out.println(database.getStringByString("translations","system.general.enabled", "translation_key", "EnUs"));
        //database.setStringByString("translations","a", "translation_key", "EnUs", "Disabled the plugin");
        //database.addLine("translations", "translation_key", UUID.randomUUID().toString());

        plugin = this;
        //if (!new File(this.getDataFolder(), "config.yml").exists())
        //    System.out.println("[JavaEngine] Config file not found, creating new one");


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
