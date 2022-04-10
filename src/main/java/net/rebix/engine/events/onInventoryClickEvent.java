package net.rebix.engine.events;

import net.rebix.engine.util.enums.ButtonAction;
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
        if(event.getCurrentItem() != null){
        ItemProperties properties = new ItemProperties(event.getCurrentItem());
        if(properties.getCannotBePickedUp()) event.setCancelled(true);
        if(properties.getButtonAction() != null){
            Bukkit.getPluginManager().callEvent(new ButtonClickEvent((Player) event.getWhoClicked(), event.getAction(), properties.getButtonAction()));
            event.setCancelled(true);
        }
        }
    }
}
