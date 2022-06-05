package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_ENCHANTED_DIAMOND extends EngineItem {
    public ENGINE_ITEM_ENCHANTED_DIAMOND() {
        super(new ItemBuilder(Material.DIAMOND, "ENGINE_ITEM:ENCHANTED_DIAMOND").setName("§7Enchanted Diamond").setGlowing(true));
    }
}
