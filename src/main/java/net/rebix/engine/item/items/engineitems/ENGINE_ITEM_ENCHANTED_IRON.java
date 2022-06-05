package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_ENCHANTED_IRON extends EngineItem {
    public ENGINE_ITEM_ENCHANTED_IRON() {
        super(new ItemBuilder(Material.IRON_INGOT, "ENGINE_ITEM:ENCHANTED_IRON").setName("§7Enchanted Iron").setGlowing(true));
    }
}
