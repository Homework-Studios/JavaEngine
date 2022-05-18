package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.api.Translator;
import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_EXIT_BUTTON extends EngineItem {
    public ENGINE_ITEM_EXIT_BUTTON() {
        super(new ItemBuilder(Material.BARRIER,"ENGINE_ITEM:EXIT_BUTTON").setButtonAction("BUTTON.ACTION.EXIT").setPickupabel(false).setName(new Translator().Translate("engine.button.exit")));
    }
}
