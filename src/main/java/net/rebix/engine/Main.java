package net.rebix.engine;

import net.rebix.engine.api.Translator;
import net.rebix.engine.api.packets.TabListAPI;
import net.rebix.engine.api.scoreboard.CustomScoreboard;
import net.rebix.engine.commands.WriteDefaultCfgCommand;
import net.rebix.engine.updater.UpdatePlugin;
import net.rebix.engine.util.Registry;
import net.rebix.engine.util.enums.LanguageType;
import org.bukkit.Bukkit;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import java.io.File;
import java.util.Objects;

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

        CustomScoreboard scoreboard = new CustomScoreboard("Test", DisplaySlot.SIDEBAR);
        scoreboard.setLine(0,"Test");
        scoreboard.setLine(14,"Test2");
        scoreboard.sendScoreboardToPlayer(Objects.requireNonNull(Bukkit.getPlayer("10MilesDeep")));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
