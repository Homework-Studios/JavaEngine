package net.rebix.engine.events.player;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class onPlayerInventoryCloseEvent implements Listener {
    @EventHandler
    public void PlayerInventoryCloseEvent(InventoryCloseEvent event) {
        switch (event.getPlayer().getOpenInventory().getTitle()) {
            case "CraftingTable 3x3":
                for (int id = 1; id <= 3; id++)
                    for (int i = 1 + id*9; i < 4 + id*9; i++) if(event.getPlayer().getOpenInventory().getTopInventory().getItem(i) != null) event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), event.getPlayer().getOpenInventory().getTopInventory().getItem(i));
                break;
            case "CraftingTable 5x5":
                for (int id = 0; id <= 4; id++)
                    for (int i = 0 + id*9; i < 5 + id*9; i++) if(event.getPlayer().getOpenInventory().getTopInventory().getItem(i) != null) event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), event.getPlayer().getOpenInventory().getTopInventory().getItem(i));
                break;
        }
    }
}
