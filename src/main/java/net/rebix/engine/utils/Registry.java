package net.rebix.engine.utils;

import net.rebix.engine.JavaEngine;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.abilities.Ability_Nuclear_blast;
import net.rebix.engine.item.modifier.Modifier;
import net.rebix.engine.utils.commands.GiveItemCommand;
import net.rebix.engine.utils.commands.JavaEngineCommand;
import net.rebix.engine.utils.customevents.playerItemUseEvent;
import net.rebix.engine.utils.events.PlayerInventoryClickEvent;
import net.rebix.engine.utils.events.PlayerInventoryPickupEvent;
import net.rebix.engine.utils.events.PlayerItemHeldEvent;
import net.rebix.engine.utils.loops.tensecloop;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    public static List<Listener> listeners = new ArrayList<>();

    public Registry(){
        registerCommand("giveitem", new GiveItemCommand());
        registerCommand("javaengine", new JavaEngineCommand());

        new Ability_Nuclear_blast();

        registerListeners(new EItem(), new playerItemUseEvent(), new PlayerInventoryClickEvent(), new PlayerItemHeldEvent(), new PlayerInventoryPickupEvent());

        Modifier.registerAll();
        EItem.registerAll();


        new tensecloop();
    }

    public static void registerListeners(Listener... listener){
        for (Listener l: listener)
            Bukkit.getPluginManager().registerEvents(l, JavaEngine.plugin);
    }

    public static void registerCommand(String command, CommandExecutor executor){
       Bukkit.getPluginCommand(command).setExecutor(executor);
    }
}
