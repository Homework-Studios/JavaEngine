package net.rebix.engine.utils.events;

import net.rebix.engine.menus.Menu;
import net.rebix.engine.menus.MenuManger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class PlayerInventoryCloseEvent implements Listener {


    @EventHandler
    public void onPlayerInventoryCloseEvent(InventoryCloseEvent event) {
        if(event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            Menu menu = MenuManger.menus.get(player);
            if(menu != null) {
                menu.onLeave();
                MenuManger.menus.remove(player);
            }
        }
    }
}
