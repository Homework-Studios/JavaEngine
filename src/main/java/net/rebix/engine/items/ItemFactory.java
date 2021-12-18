package net.rebix.engine.items;

import net.rebix.engine.api.PlayerHead;
import net.rebix.engine.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemFactory {
    //Utility Items
    public static ItemStack PLACEHOLDER = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setPickupabel(false).setName(" ").build();

}
