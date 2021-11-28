package net.rebix.engine.util.events;

import net.rebix.engine.Main;
import net.rebix.engine.util.api.inventorycomponents.ButtonAction;
import net.rebix.engine.util.api.propertie.ItemProperties;
import net.rebix.engine.util.events.customevents.ButtonClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onInventoryClickEvent implements Listener {
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        if(event.getCurrentItem() != null){
        ItemProperties properties = new ItemProperties(event.getCurrentItem());
        if(properties.getCannotBePickedUp()) event.setCancelled(true);
        if(properties.getButtonAction() != ButtonAction.NONE) {
            switch (event.getAction()) {
                case PICKUP_ALL:
                    Bukkit.getPluginManager().callEvent(new ButtonClickEvent((Player) event.getWhoClicked(), properties.getButtonAction()));
                    break;
                case PICKUP_HALF:
                    switch (properties.getButtonAction()){
                        case SCROLL_LEFT:
                            Bukkit.getPluginManager().callEvent(new ButtonClickEvent((Player) event.getWhoClicked(), ButtonAction.SCROLL_LEFT_FAST));
                            break;
                        case SCROLL_RIGHT:
                            Bukkit.getPluginManager().callEvent(new ButtonClickEvent((Player) event.getWhoClicked(), ButtonAction.SCROLL_RIGHT_FAST));
                            break;
                    }
                    break;
                case MOVE_TO_OTHER_INVENTORY:
                    switch (properties.getButtonAction()){
                        case SCROLL_LEFT:
                            Bukkit.getPluginManager().callEvent(new ButtonClickEvent((Player) event.getWhoClicked(), ButtonAction.SCROLL_LEFT_VERY_FAST));
                            break;
                        case SCROLL_RIGHT:
                            Bukkit.getPluginManager().callEvent(new ButtonClickEvent((Player) event.getWhoClicked(), ButtonAction.SCROLL_RIGHT_VERY_FAST));
                            break;
                    }
                    break;
            }
            event.setCancelled(true);
        }
        }
    }
}
