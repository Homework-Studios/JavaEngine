package net.rebix.engine.utils.customevents;

import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.ItemAbilityType;
import net.rebix.engine.item.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class playerItemUseEvent implements Listener {

@EventHandler
    public void onPlayerItemUse(PlayerInteractEvent event) {
    switch (event.getAction()) {
        case RIGHT_CLICK_BLOCK:
            /*
            Material[] doors = {Material.ACACIA_DOOR, Material.BIRCH_DOOR, Material.DARK_OAK_DOOR, Material.OAK_DOOR, Material.JUNGLE_DOOR, Material.SPRUCE_DOOR};
            for(Material door : doors) if(event.getClickedBlock().getType().equals(door)) {
                if(!event.getPlayer().isSneaking() && JavaEngine.plugin.getConfig().getBoolean("DoorOpenerEnabled"))
                    DoorOpener.DoorUpdate( event.getClickedBlock());
            }
*/

            break;
    }
    ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
    if(new ItemStackBuilder(item).getID() != null)
        if(item.getType() != Material.AIR) {
                    Bukkit.getPluginManager().callEvent(new ItemUseAbilityEvent(event.getPlayer(), new EItem(item), ItemAbilityType.translateFromInteractEvent(event.getAction(), event.getPlayer().isSneaking())));
        }

}
}
