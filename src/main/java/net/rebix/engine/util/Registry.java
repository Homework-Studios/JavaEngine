package net.rebix.engine.util;

import net.rebix.engine.Main;
import net.rebix.engine.api.Translator;
import net.rebix.engine.commands.*;
import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.events.*;
import net.rebix.engine.items.ItemFactory;
import net.rebix.engine.util.enums.LanguageType;
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
        Objects.requireNonNull(Bukkit.getPluginCommand("/item")).setExecutor(new ItemCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("/spawn")).setExecutor(new SpawnCommand());

        new Translator().enable();
        new cfgManager().enable();
        new ItemFactory().enable();
        new ItemCommand().enable();

        Main.Language = LanguageType.valueOf(Main.plugin.getConfig().getString("Language"));
        Bukkit.getLogger().info(new Translator().Translate("engine.load"));

    }
}
