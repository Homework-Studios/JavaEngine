package net.rebix.engine.qol;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;


public class CropHarvester implements Listener {
    HashMap<Material, Material> cropSeed = new HashMap<>();
    Material[] crops = {Material.WHEAT, Material.CARROT, Material.POTATO, Material.BEETROOT};

    @EventHandler
    void onPlayerInteractEvent(PlayerInteractEvent event) {
        cropSeed.put(Material.WHEAT, Material.WHEAT_SEEDS);
        cropSeed.put(Material.CARROT, Material.CARROT);
        cropSeed.put(Material.POTATO, Material.POTATO);
        cropSeed.put(Material.BEETROOT, Material.BEETROOT_SEEDS);
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            for (Material crop : crops) {
                if (event.getClickedBlock().getType() == crop) {
                    harvestCrop(event.getClickedBlock());
                }
            }
        }
    }

    void harvestCrop(Block block) {
        Collection<ItemStack> drops = block.getDrops();
        drops.remove(cropSeed.get(block.getType()));
        System.out.println(block.getBlockData());
    }
}
