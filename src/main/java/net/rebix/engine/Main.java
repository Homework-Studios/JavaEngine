package net.rebix.engine;

import net.rebix.engine.api.Translator;
import net.rebix.engine.commands.ItemCommand;
import net.rebix.engine.commands.WriteDefaultCfgCommand;
import net.rebix.engine.items.ItemFactory;
import net.rebix.engine.updater.UpdatePlugin;
import net.rebix.engine.util.Registry;
import net.rebix.engine.util.cfgManager;
import net.rebix.engine.util.enums.LanguageType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    public static Integer INTEGER_LIMIT = 2147483647;
    public static LanguageType Language = LanguageType.English;

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        if(!new File(this.getDataFolder(),"config.yml").exists()) new WriteDefaultCfgCommand().write();

        //Removing unwanted Entity's with scoreboard Tag: RemoveEntityOnDisable
        for (World world: Bukkit.getWorlds())
            for (Entity entity: world.getEntities())
                if(entity.getScoreboardTags().contains("RemoveEntityOnDisable")) entity.remove();

        new Registry();


        new Translator().enable();
        new cfgManager().enable();
        new ItemFactory().enable();
        new ItemCommand().enable();

        Main.Language = LanguageType.valueOf(Main.plugin.getConfig().getString("Language"));
        Bukkit.getLogger().info(new Translator().Translate("engine.load"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
