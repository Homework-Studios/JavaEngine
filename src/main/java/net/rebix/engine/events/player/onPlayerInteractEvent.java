package net.rebix.engine.events.player;

import net.rebix.engine.Main;
import net.rebix.engine.qol.DoorOpener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class onPlayerInteractEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        switch (event.getAction()) {
            case RIGHT_CLICK_BLOCK:
                 if(event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.CRAFTING_TABLE) && Main.plugin.getConfig().getBoolean("CustomCraftingTable")) {
                    Main.getCraftingManager().open3x3(event.getPlayer());
                    event.setCancelled(true);
                }

                Material[] doors = {Material.ACACIA_DOOR, Material.BIRCH_DOOR, Material.DARK_OAK_DOOR, Material.OAK_DOOR, Material.JUNGLE_DOOR, Material.SPRUCE_DOOR};
                for(Material door : doors) if(event.getClickedBlock().getType().equals(door)) {
                    if(!event.getPlayer().isSneaking() && Main.plugin.getConfig().getBoolean("DoorOpenerEnabled"))
                    DoorOpener.DoorUpdate( event.getClickedBlock());
                }


                break;
        }
    }
}
