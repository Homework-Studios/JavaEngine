package net.rebix.engine.items;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemFactory {
    public static HashMap<String, ItemStack> Items = new HashMap<>();

    public void enable() {
        registerItem(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "PLACEHOLDER").setPickupabel(false).setName(" ").build());

        new ShapedNormalRecipe("enchanted_golden_apple", new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE,"").build(),Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.APPLE,Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.GOLD_BLOCK);
    }

    public void registerItem(ItemStack item) {
        Items.put(new ItemBuilder(item).getID(),item);
    }

    public List<String> getAllKeys() {
        List<String> out = new ArrayList<>();
        for (String a: Items.keySet()) {
            out.add(a);
        }
        return out;
    }
}
