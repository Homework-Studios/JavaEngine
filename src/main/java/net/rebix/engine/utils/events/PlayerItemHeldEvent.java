package net.rebix.engine.utils.events;

import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemStackBuilder;
import net.rebix.engine.item.modifier.Modifier;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class PlayerItemHeldEvent implements Listener {

    @EventHandler
    public void PlayerItemHeldEvent(org.bukkit.event.player.PlayerItemHeldEvent event) {
        Inventory inventory = event.getPlayer().getInventory();
        if(inventory.getItem(event.getNewSlot()) != null) {
            if (inventory.getItem(event.getNewSlot()).getType() != Material.AIR && inventory.getItem(event.getNewSlot()).getItemMeta().getLocalizedName().contains("eitem"))
                new EItem(Objects.requireNonNull(inventory.getItem(event.getNewSlot()))).updateItem(inventory, event.getNewSlot());
            if(inventory.getItem(event.getNewSlot()).getType() != Material.AIR && inventory.getItem(event.getNewSlot()).getItemMeta().getLocalizedName().contains("modifier"))
                new Modifier(Objects.requireNonNull(inventory.getItem(event.getNewSlot()))).updateItem(inventory, event.getNewSlot());
        }
    }
}
