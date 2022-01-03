package net.rebix.engine;

import net.rebix.engine.commands.WriteDefaultCfgCommand;
import net.rebix.engine.util.Registry;
import net.rebix.engine.api.playernametag.PlayerSetNameTag;
import net.rebix.engine.api.Translator;
import net.rebix.engine.updater.UpdatePlugin;
import net.rebix.engine.util.enums.LanguageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {
    public static Integer INTEGER_LIMIT = 2147483647;
    public static LanguageType Language = LanguageType.English;

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        if(!new File(this.getDataFolder(),"config.yml").exists()) new WriteDefaultCfgCommand().write();
        new Registry();

        if(this.getConfig().getDouble("Version") < Double.parseDouble(new Translator().Translate("engine.version"))) new UpdatePlugin();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
