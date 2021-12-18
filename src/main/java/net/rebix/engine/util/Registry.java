package net.rebix.engine.util;

import net.rebix.engine.Main;
import net.rebix.engine.commands.ReloadCommand;
import net.rebix.engine.commands.TestCommand;
import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.commands.WriteDefaultCfgCommand;
import net.rebix.engine.events.*;
import org.bukkit.Bukkit;

import java.util.Objects;

public class Registry {
    public Registry(){
        Bukkit.getPluginManager().registerEvents(new onInventoryClickEvent(), Main.plugin);
        Bukkit.getPluginManager().registerEvents(new ScrollableInventory(), Main.plugin);
        Bukkit.getPluginManager().registerEvents(new onPlayerJoinEvent(),Main.plugin);
        Bukkit.getPluginManager().registerEvents(new onPlayerLeaveEvent(),Main.plugin);
        Bukkit.getPluginManager().registerEvents(new onPlayerRespawnEvent(),Main.plugin);

        Objects.requireNonNull(Bukkit.getPluginCommand("test")).setExecutor(new TestCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("writedefaultcfg")).setExecutor(new WriteDefaultCfgCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("/r")).setExecutor(new ReloadCommand());

        new Translator().enable();
        new cfgManager().enable();

        Bukkit.getLogger().info(new Translator().Translate("engine.load", Main.defaultLanguage));
    }
}
