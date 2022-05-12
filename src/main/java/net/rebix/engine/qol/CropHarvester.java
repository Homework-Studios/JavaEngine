package net.rebix.engine.qol;

import net.rebix.engine.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.Random;


public class CropHarvester implements Listener {


    @EventHandler
    void onPlayerInteractEvent(PlayerInteractEvent event) {
        ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
        Block block = event.getClickedBlock();
        if (hand.getType() != Material.AIR)
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && hand.getType().name().contains("HOE")) {
            String blockData = block.getBlockData().getAsString();
            switch (block.getType()) {
                case WHEAT:
                    if(blockData.contains("age=7"))
                        for (ItemStack item :block.getDrops()) {
                            if(item.getType() == Material.WHEAT_SEEDS) item.setAmount(item.getAmount()-1);
                            if(item.getType() != Material.AIR && item.getAmount() > 0)
                            block.getWorld().dropItemNaturally(block.getLocation(), item);
                            removeAge(block , hand);
                        }
                    break;
                case CARROTS:
                case POTATOES:
                case NETHER_WART:
                    if(blockData.contains("age=7")) dropSeeds(block, hand);break;
                case BEETROOTS:
                    if(blockData.contains("age=3"))
                        for (ItemStack item :block.getDrops()) {
                            if(item.getType() == Material.BEETROOT_SEEDS) item.setAmount(item.getAmount()-1);
                            if(item.getType() != Material.AIR && item.getAmount() > 0)
                            block.getWorld().dropItemNaturally(block.getLocation(), item);
                            removeAge(block, hand);
                        }break;
                    }}}

    void dropSeeds(Block block, ItemStack hand) {
        for (ItemStack item :block.getDrops()) {
            if(item.getType() != Material.AIR)
           block.getWorld().dropItemNaturally(block.getLocation(), item);
        }
        removeAge(block , hand);
    }
    void removeAge(Block block, ItemStack hand) {
        String blockData = block.getBlockData().getAsString();
        blockData = blockData.replaceAll("age=\\d+", "age=0");
        block.setBlockData(Bukkit.createBlockData(blockData));

        Damageable meta = (Damageable) hand.getItemMeta();
        int unbreaking = meta.getEnchantLevel(Enchantment.DURABILITY);
        Double chance = 10 - unbreaking * unbreaking* Main.plugin.getConfig().getDouble("hoe_unbreakingenchant_modifier");
        Integer random = new Random().nextInt(10);
        if(random >= chance) {
            meta.setDamage(meta.getDamage() + 1);
            hand.setItemMeta(meta);
        }
    }
}
