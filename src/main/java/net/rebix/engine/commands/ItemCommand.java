package net.rebix.engine.commands;

import net.rebix.engine.item.ItemFactory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemCommand implements CommandExecutor, TabCompleter {
    public static List<String> args0 = new ArrayList<>();

    public void enable() {
        args0.add(0,"list");
        args0.add(1,"get");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("engine.itemcommand.use"))
        switch (args[0]) {
            case "list":
                sender.sendMessage("All items with id:");
                for (String item : new ItemFactory().getAllKeys()) {
                    sender.sendMessage("-> "+ item);
                }
                break;
            case "get":
                if(args[1] != null) {
                    if(sender instanceof Player) ((Player) sender).getInventory().addItem(ItemFactory.Items.get(args[1]));
                } else sender.sendMessage("§4Missing args");
                break;
        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> out = args0;
        switch (args[0]) {
            case "get":
                out = new ItemFactory().getAllKeys();
                break;

            case "list":
                out = null;
                break;
        }

        return out;
    }
}
