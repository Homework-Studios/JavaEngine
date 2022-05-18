package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_NOTHING extends EngineItem {
    public ENGINE_ITEM_NOTHING() {
        super(new ItemBuilder(Material.RED_STAINED_GLASS_PANE,"NOTHING").setPickupabel(false).setName("§4Nothing"));
    }
}
