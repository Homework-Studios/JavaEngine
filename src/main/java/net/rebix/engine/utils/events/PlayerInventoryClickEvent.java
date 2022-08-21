package net.rebix.engine.utils.events;

import net.rebix.engine.JavaEngine;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemStackBuilder;
import net.rebix.engine.menus.menus.InspectorMenu;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class PlayerInventoryClickEvent implements Listener {

    @EventHandler
    public void onInventoryDragEvent(InventoryClickEvent event) {
        if(event.getAction().equals(InventoryAction.PICKUP_HALF)) {
            if(EItem.isEItem(event.getCurrentItem())) {
                EItem eItem = new EItem(Objects.requireNonNull(event.getCurrentItem()));
                new InspectorMenu(eItem.getName(), 5, event.getView().getTopInventory(), eItem).setPlayer((Player) event.getWhoClicked());
                event.setCancelled(true);
            }
        }



        if(event.getCurrentItem() != null) {
            if(event.getCurrentItem().getType() == Material.AIR) return;
            if(event.getCurrentItem().getItemMeta() != null) {
                String pickup = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "pickupabel"), PersistentDataType.STRING);
                if(pickup != null) {
                    if(pickup.equals("false")) {
                        event.setCancelled(true);
                    }
                }
            }
        }
           // if(event.getCurrentItem().getType() != Material.AIR && new ItemStackBuilder(event.getCurrentItem()).getID() != null);
                //new EItem(event.getCurrentItem()).updateItem(event.getInventory(), event.getSlot());
    }
}
