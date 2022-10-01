package net.rebix.engine.utils;

import net.rebix.engine.JavaEngine;
import net.rebix.engine.combat.DamageEvents;
import net.rebix.engine.combat.onPlayerToggleSneakEvent;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.abilities.Ability_Ether_Transmission;
import net.rebix.engine.item.ItemAbility.abilities.Ability_Instant_Transmission;
import net.rebix.engine.item.ItemAbility.abilities.Ability_Nuclear_blast;
import net.rebix.engine.item.ItemAbility.abilities.ItemAbilityGravityStrom;
import net.rebix.engine.item.modifier.Modifier;
import net.rebix.engine.utils.commands.GiveItemCommand;
import net.rebix.engine.utils.commands.JavaEngineCommand;
import net.rebix.engine.utils.customevents.playerItemUseEvent;
import net.rebix.engine.utils.events.*;
import net.rebix.engine.utils.loops.secloop;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Registry {

    public Registry() {
        registerCommand("giveitem", new GiveItemCommand());
        registerCommand("javaengine", new JavaEngineCommand());

        new Ability_Nuclear_blast();
        new Ability_Instant_Transmission();
        new Ability_Ether_Transmission();


        registerListeners(new EItem(), new playerItemUseEvent(), new PlayerInventoryClickEvent(), new PlayerItemHeldEvent(),
                new PlayerInventoryPickupEvent(), new PlayerInventoryCloseEvent(), new PlayerJoinEvent(), new DamageEvents(),
                new PlayerFoodLevelChangeEvent(), new onPlayerToggleSneakEvent(), new ItemAbilityGravityStrom());

        Modifier.registerAll();
        EItem.registerAll();

        new secloop();

    }

    public static void registerListeners(Listener... listener) {
        for (Listener l : listener)
            Bukkit.getPluginManager().registerEvents(l, JavaEngine.plugin);
    }

    public static void registerCommand(String command, CommandExecutor executor) {
        Bukkit.getPluginCommand(command).setExecutor(executor);
    }
}
