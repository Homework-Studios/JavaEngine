package net.rebix.engine.utils.events;

import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemStackBuilder;
import net.rebix.engine.item.modifier.Modifier;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class PlayerInventoryPickupEvent implements Listener {
    @EventHandler
    public void onItemPickup(InventoryPickupItemEvent event) {
        Inventory inventory = event.getInventory();
        if(event.getItem().getItemStack().getType() != Material.AIR && event.getItem().getItemStack().getItemMeta().getLocalizedName().contains("eitem"))
            new EItem(Objects.requireNonNull(event.getItem().getItemStack())).updateItem(inventory, inventory.firstEmpty());
        if(event.getItem().getItemStack().getType() != Material.AIR && event.getItem().getItemStack().getItemMeta().getLocalizedName().contains("modifier"))
            new Modifier(Objects.requireNonNull(event.getItem().getItemStack())).updateItem(inventory, inventory.firstEmpty());
    }
}
