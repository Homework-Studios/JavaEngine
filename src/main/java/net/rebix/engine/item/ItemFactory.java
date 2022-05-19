package net.rebix.engine.item;

import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.items.engineitems.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemFactory {
    public static HashMap<String, ItemStack> Items = new HashMap<>();
    public static HashMap<String, EngineItem> ItemsEngineItem = new HashMap<>();
    public static HashMap<String, ItemAbility> ItemsAbility = new HashMap<>();



    public void enable() {
       new ENGINE_ITEM_EXIT_BUTTON().register();
       new ENGINE_ITEM_NOTHING().register();
       new ENGINE_ITEM_PLACEHOLDER().register();
       new ENGINE_ITEM_TEST_FURY().register();
       new ENGINE_ITEM_TEST_SWORD().register();

        new ShapedNormalRecipe("enchanted_golden_apple", new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE,"").build(),Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.APPLE,Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.GOLD_BLOCK,Material.GOLD_BLOCK);
    }

    public void registerItem(ItemStack item, EngineItem engineItem) {
        Items.put(new ItemBuilder(item).getID(),item);
        ItemsEngineItem.put(new ItemBuilder(item).getID(),engineItem);
    }

    public static void registerAbility(String id, ItemAbility ability) {
        ItemsAbility.put(id, ability);
    }

    public List<String> getAllKeys() {
        List<String> out = new ArrayList<>();
        for (String a: Items.keySet()) {
            out.add(a);
        }
        return out;
    }

    public static EngineItem getItemByItemStack(ItemStack item) {
        return ItemsEngineItem.get(new ItemBuilder(item).getID());
    }
}
