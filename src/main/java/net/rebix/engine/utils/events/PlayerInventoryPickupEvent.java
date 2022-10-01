package net.rebix.engine.utils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;

public class PlayerInventoryPickupEvent implements Listener {
    @EventHandler
    public void onItemPickup(InventoryPickupItemEvent event) {
        Inventory inventory = event.getInventory();
        /*
        if(event.getItem().getItemStack().getType() != Material.AIR && event.getItem().getItemStack().getItemMeta().getLocalizedName().contains("eitem"))
            new EItem(Objects.requireNonNull(event.getItem().getItemStack())).updateItem(inventory, inventory.firstEmpty());
        if(event.getItem().getItemStack().getType() != Material.AIR && event.getItem().getItemStack().getItemMeta().getLocalizedName().contains("modifier"))
            new Modifier(Objects.requireNonNull(event.getItem().getItemStack())).updateItem(inventory, inventory.firstEmpty());

         */
    }
}
