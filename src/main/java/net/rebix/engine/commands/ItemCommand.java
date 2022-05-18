package net.rebix.engine.commands;

import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.item.ItemBuilder;
import net.rebix.engine.item.ItemFactory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemCommand implements CommandExecutor, TabCompleter {
    public static List<String> args0 = new ArrayList<>();

    public void enable() {
        args0.add(0,"list");
        args0.add(1,"get");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("engine.itemcommand.use")) {

            if(args.length > 1)
            switch (args[0]) {
                case "list":
                    sender.sendMessage("All items with id:");
                    for (String item : new ItemFactory().getAllKeys()) {
                        sender.sendMessage("-> " + item);
                    }
                    break;
                case "get":
                    if (args[1] != null) {
                        if (sender instanceof Player)
                            ((Player) sender).getInventory().addItem(ItemFactory.Items.get(args[1]));
                    } else sender.sendMessage("§4Missing args");
                    break;
            }
            else {
                ScrollableInventory inv = new ScrollableInventory().create((Player) sender, "§6Get Items", 54);

                ;
                HashMap<Integer, ItemStack> items = new HashMap<>();
                for (int i = 0; i < ItemFactory.ItemsEngineItem.keySet().size(); i++) {

                    ItemStack item = ItemFactory.ItemsEngineItem.get(ItemFactory.ItemsEngineItem.keySet().toArray()[i]).getItem();
                    item = new ItemBuilder(item).setPickupabel(false).setButtonAction("BUTTONACTION.GIVECLICKED:BYID").build();
                    items.put(i, item);
                }
                inv.setContents(items);
            }
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
