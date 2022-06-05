package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_ENCHANTED_NETHER_STAR extends EngineItem {
    public ENGINE_ITEM_ENCHANTED_NETHER_STAR() {
        super(new ItemBuilder(Material.NETHER_STAR,"ENCHANTED_NETHER_STAR").setName("§6Enchanted Nether Star").setLore("§7This item is a §6Enchanted Nether Star§7.").setGlowing(true).build());
    }
}
