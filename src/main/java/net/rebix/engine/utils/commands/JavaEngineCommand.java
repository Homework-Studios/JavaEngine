package net.rebix.engine.utils.commands;

import net.rebix.engine.EPlayer;
import net.rebix.engine.JavaEngine;
import net.rebix.engine.api.stuff.WaveGenerator;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.modifier.Modifier;
import net.rebix.engine.menus.menus.MainMenu;
import net.rebix.engine.utils.variables.Vector2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.Buffer;
import java.security.PublicKey;
import java.util.*;

//0.04 x^(3)+0.8 x^(2)+2 x   copilot: ^(1)+1
public class JavaEngineCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length > 0)
            switch (args[0]) {
                case "reregister":
                    sender.sendMessage("Reregistering...");
                    EItem.registered.clear();
                    EItem.registerAll();
                    sender.sendMessage("Reregistered!");
                    break;
                case "reload":
                    sender.sendMessage("Reloading...");
                    Bukkit.reload();
                    sender.sendMessage("Reloaded!");
                    break;
                case "upgrade":

                    if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                        EItem eItem = new EItem(player.getInventory().getItemInMainHand());
                        if (args.length > 1)
                            eItem.upgrade(Integer.parseInt(args[1]));
                        else
                            eItem.upgrade();
                        eItem.updateItem(player.getInventory(), player.getInventory().getHeldItemSlot());
                    }
                    break;
                case "max":
                    
                    if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                        EItem eItem = new EItem(player.getInventory().getItemInMainHand());
                        eItem.max();
                        eItem.updateItem(player.getInventory(), player.getInventory().getHeldItemSlot());
                    }
                    break;
                case "modifier":
                    
                    player.getInventory().addItem(Modifier.get(args[1]).getItem());
                    break;
                case "health":
                    
                    EPlayer ePlayer = EPlayer.get(player);
                    if(args.length > 1) {
                        ePlayer.setHealth(Integer.parseInt(args[1]));
                    }
                    else sender.sendMessage("Health: " + ePlayer.getHealth());
                    break;
                case "test":
                    new WaveGenerator().generateShockWave(100, false, player.getLocation(), 5L, false, EPlayer.get(player));

                    break;
            } else new MainMenu().setPlayer((Player) sender);
        return false;

    }

public int i = 0;




    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        for (Modifier eItem : Modifier.registered.values())
            list.add(eItem.getId());
        if(strings.length < 2)
            return Arrays.asList("reregister","reload","upgrade","max","modifier", "health", "test");
        return list;
}
}
