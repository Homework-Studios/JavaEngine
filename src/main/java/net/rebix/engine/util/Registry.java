package net.rebix.engine.util;

import net.rebix.engine.Main;
import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.api.Translator;
import net.rebix.engine.commands.*;
import net.rebix.engine.events.block.onBlockBreakEvent;
import net.rebix.engine.events.block.onBlockPlaceEvent;
import net.rebix.engine.events.onButtonClickEvent;
import net.rebix.engine.events.onInventoryClickEvent;
import net.rebix.engine.events.player.*;
import net.rebix.engine.item.ItemBuilder;
import net.rebix.engine.qol.CropHarvester;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.FurnaceRecipe;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    public static List<Listener> listeners = new ArrayList<>();

    public Registry(){

        //Inventory Events
        registerListener(new onInventoryClickEvent());
        registerListener(new ScrollableInventory());
        registerListener(Main.getCraftingManager());
        //player Events
        registerListener(new onPlayerJoinEvent());
        registerListener(new onPlayerLeaveEvent());
        registerListener(new onPlayerRespawnEvent());
        registerListener(new onPlayerDeathEvent());
        registerListener(new onPlayerItemHeldEvent());
        registerListener(new onPlayerChatEvent());
        registerListener(new onPlayerSneakEvent());
        registerListener(new onPlayerInteractEvent());
        registerListener(new onPlayerInventoryCloseEvent());
        registerListener(new CropHarvester());
        registerListener(new onPlayerDropItemEvent());
        registerListener(new onPlayerUseAbilityEvent());
        //block Events
        registerListener(new onBlockPlaceEvent());
        registerListener(new onBlockBreakEvent());
        registerListener(new onButtonClickEvent());




        for (Listener listener: listeners)
            Bukkit.getPluginManager().registerEvents(listener, Main.plugin);

        //Registering Commands
        registerCommand("test", new TestCommand());
        registerCommand("/time", new TimeCommand());
        registerCommand("writedefaultcfg", new WriteDefaultCfgCommand());
        registerCommand("/r", new ReloadCommand());
        registerCommand("/item", new ItemCommand());
        registerCommand("/itembuilder", new ItemBuilderCommand());
        registerCommand("/translate", new TranslateCommand());
        registerCommand("/spawn", new SpawnCommand());
        registerCommand("/day", new DayCommand());
        registerCommand("/craft", new CraftCommand());
        registerCommand("/rename", new RenameCommand());
        registerCommand("/world_menu", new WorldCommand());


        FurnaceRecipe furnaceRecipe = new FurnaceRecipe(NamespacedKey.minecraft("tropical_fish_smelt"),new ItemBuilder(Material.COOKED_SALMON).setName(new Translator().Translate("engine.vanillaitem.coockedtropical")).build(),Material.TROPICAL_FISH,5.0f,20);
        Bukkit.getServer().addRecipe(furnaceRecipe);
    }

    public static void registerListener(Listener listener){
        listeners.add(listener);
    }

    public static void registerCommand(String command, CommandExecutor executor){
       Bukkit.getPluginCommand(command).setExecutor(executor);
    }
}
