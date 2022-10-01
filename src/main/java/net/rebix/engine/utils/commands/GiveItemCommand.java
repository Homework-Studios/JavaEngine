package net.rebix.engine.utils.commands;

import net.rebix.engine.api.inventory.ScrollableInventory;
import net.rebix.engine.item.EItem;
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

public class GiveItemCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        Player player = (Player) commandSender;
        if (args.length == 0) {
            ScrollableInventory inventory = new ScrollableInventory().create(player, "Items", 54);
            HashMap<Integer, ItemStack> items = new HashMap<>();
            for (int i = 0; i < EItem.registered.values().size(); i++) {
                ItemStack stack = EItem.registered.values().toArray(new EItem[0])[i].getItem();
                items.put(i, stack);
            }
            inventory.setContents(items);
            inventory.reloadInventory();
            return true;
        }
        EItem eItem = new EItem(args[0]);
        player.getInventory().addItem(eItem.getItem());
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        for (EItem eItem : EItem.registered.values())
            list.add(eItem.getId());

        return list;
    }
}
