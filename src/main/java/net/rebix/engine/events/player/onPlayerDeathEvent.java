package net.rebix.engine.events.player;

import net.rebix.engine.Main;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class onPlayerDeathEvent implements Listener {
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        ItemStack playerskull = new ItemBuilder(Material.PLAYER_HEAD).build();
        SkullMeta skullMeta = (SkullMeta) playerskull.getItemMeta();
        skullMeta.setOwningPlayer(event.getEntity().getPlayer());
        playerskull.setItemMeta(skullMeta);
        if(Main.plugin.getConfig().getBoolean("HeadDrop")) event.getEntity().getWorld().dropItem(event.getEntity().getLocation(),playerskull);
    }
}
