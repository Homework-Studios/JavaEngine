package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_ENCHANTED_IRON_BLOCK extends EngineItem {
    public ENGINE_ITEM_ENCHANTED_IRON_BLOCK() {
        super(new ItemBuilder(Material.IRON_BLOCK, "ENGINE_ITEM:EMCHANTED_IRON_BLOCK").setName("§7Enchanted Iron Block").setGlowing(true));
    }
}
