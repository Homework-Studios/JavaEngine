package net.rebix.engine.util;

import net.rebix.engine.Main;
import net.rebix.engine.TestCommand;
import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.events.*;
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

        new Translator().enable();

        System.out.println(new Translator().Translate("JavaEngine was loadet", LanguageType.German));
    }
}
