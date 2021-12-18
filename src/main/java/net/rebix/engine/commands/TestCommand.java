package net.rebix.engine.commands;

import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ScrollableInventory scrollable_inventory = new ScrollableInventory().create((Player) sender,"test",6*9);
        HashMap<Integer,ItemStack> contents = new HashMap<>();
        int index;
        for(index = 1; index < 63; ++index){
            contents.put(index, new ItemBuilder(Material.STONE).setAmount(index).build());
        }
        scrollable_inventory.setContents(contents);
        scrollable_inventory.reloadInventory();
        return false;
    }
}
