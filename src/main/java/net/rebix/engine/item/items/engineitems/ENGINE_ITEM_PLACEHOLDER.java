package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_PLACEHOLDER extends EngineItem {

    public ENGINE_ITEM_PLACEHOLDER() {
        super(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, "ENGINE_ITEM:PLACEHOLDER").setName(" ").setPickupabel(false));
    }
}
