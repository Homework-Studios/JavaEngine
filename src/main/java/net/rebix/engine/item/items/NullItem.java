package net.rebix.engine.item.items;

import net.rebix.engine.item.EngineItem;
import org.bukkit.Material;

import java.util.List;

public class NullItem extends EngineItem {
    public NullItem() {
        super("NULL", "null", null, Material.BARRIER);
    }
}
