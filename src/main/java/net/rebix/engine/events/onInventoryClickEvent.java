package net.rebix.engine.events;

import net.rebix.engine.api.property.ItemProperties;
import net.rebix.engine.events.customevents.ButtonClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onInventoryClickEvent implements Listener {
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        switch (event.getAction()){
            case PICKUP_ONE:
            case PICKUP_ALL:
            case MOVE_TO_OTHER_INVENTORY:
        if(event.getCurrentItem() != null){
        ItemProperties properties = new ItemProperties(event.getCurrentItem());
        if(properties.getCannotBePickedUp()) event.setCancelled(true);
        if(properties.getButtonAction() != null) {
            Bukkit.getPluginManager().callEvent(new ButtonClickEvent((Player) event.getWhoClicked(), event.getAction(), properties.getButtonAction(), event.getCurrentItem()));
        }
        }
        }
    }
}
