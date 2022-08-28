package net.rebix.engine.utils.commands;

import net.rebix.engine.EPlayer;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.modifier.Modifier;
import net.rebix.engine.menus.menus.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//0.04 x^(3)+0.8 x^(2)+2 x   copilot: ^(1)+1
public class JavaEngineCommand implements CommandExecutor, TabCompleter {
    public int i = 0;

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        Player player = new EPlayer((Player) sender);
        if (args.length > 0)
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


                    if (args.length > 1) {
                        player.setHealth(Integer.parseInt(args[1]));
                    } else sender.sendMessage("Health: " + player.getHealth());
                    break;
                case "test":


                    break;
            }
        else new MainMenu().setPlayer((Player) sender);
        return false;

    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        for (Modifier eItem : Modifier.registered.values())
            list.add(eItem.getId());
        if (strings.length < 2)
            return Arrays.asList("reregister", "reload", "upgrade", "max", "modifier", "health", "test");
        return list;
    }
}
