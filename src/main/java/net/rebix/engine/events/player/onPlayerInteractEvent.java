package net.rebix.engine.events.player;

import net.rebix.engine.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

public class onPlayerInteractEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(RIGHT_CLICK_BLOCK) && event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.CRAFTING_TABLE) && Main.plugin.getConfig().getBoolean("CraftingTableReplaced")) {
            Main.getCraftingManager().open3x3(event.getPlayer());
            event.setCancelled(true);
        }
    }
}
