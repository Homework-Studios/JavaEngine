package net.rebix.engine.util;

import net.rebix.engine.Main;
import net.rebix.engine.api.Translator;
import net.rebix.engine.commands.*;
import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.events.*;
import net.rebix.engine.events.block.onBlockBreakEvent;
import net.rebix.engine.events.block.onBlockPlaceEvent;
import net.rebix.engine.events.player.*;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.FurnaceRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Registry {

    public static List<Listener> listeners = new ArrayList<>();

    public Registry(){

        //Inventory Events
        listeners.add(new onInventoryClickEvent());
        listeners.add(new ScrollableInventory());
        listeners.add(Main.getCraftingManager());
        //player Events
        listeners.add(new onPlayerJoinEvent());
        listeners.add(new onPlayerLeaveEvent());
        listeners.add(new onPlayerRespawnEvent());
        listeners.add(new onPlayerDeathEvent());
        listeners.add(new onPlayerItemHeldEvent());
        listeners.add(new onPlayerChatEvent());
        listeners.add(new onPlayerSneakEvent());
        //block Events
        listeners.add(new onBlockPlaceEvent());
        listeners.add(new onBlockBreakEvent());
        listeners.add(new onButtonClickEvent());
        ;


        for (Listener listener: listeners)
            Bukkit.getPluginManager().registerEvents(listener, Main.plugin);


        Objects.requireNonNull(Bukkit.getPluginCommand("test")).setExecutor(new TestCommand());

        Objects.requireNonNull(Bukkit.getPluginCommand("/time")).setExecutor(new TimeCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("writedefaultcfg")).setExecutor(new WriteDefaultCfgCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("/r")).setExecutor(new ReloadCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("/item")).setExecutor(new ItemCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("/spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("/day")).setExecutor(new DayCommand());

        Objects.requireNonNull(Bukkit.getPluginCommand("/craft")).setExecutor(new CraftCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("/rename")).setExecutor(new RenameCommand());

        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(NamespacedKey.minecraft("tropical_fish_smelt"),new ItemBuilder(Material.COOKED_SALMON).setName(new Translator().Translate("engine.vanillaitem.coockedtropical")).build(),Material.TROPICAL_FISH,5.0f,20);
        Bukkit.getServer().addRecipe(furnaceRecipe);
    }



}
