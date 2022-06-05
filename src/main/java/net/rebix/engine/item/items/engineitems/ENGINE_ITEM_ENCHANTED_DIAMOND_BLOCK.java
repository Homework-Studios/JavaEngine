package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_ENCHANTED_DIAMOND_BLOCK extends EngineItem {

        public ENGINE_ITEM_ENCHANTED_DIAMOND_BLOCK() {
            super(new ItemBuilder(Material.DIAMOND_BLOCK, "ENGINE_ITEM:ENCHANTED_DIAMOND_BLOCK").setName("§7Enchanted Diamond Block").setGlowing(true));
        }
}
