package net.rebix.engine.events.player;

import net.rebix.engine.api.loadstonechunkloader.LoadStoneChunkloader;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class onPlayerItemHeldEvent implements Listener {

    @EventHandler
    public void PlayerItemHeldEvent(PlayerItemHeldEvent event) {
        if(event.getPlayer().getInventory().getItem(event.getNewSlot()) == null) return;
        if(event.getPlayer().getInventory().getItem(event.getNewSlot()).getType().equals(Material.LODESTONE)) new LoadStoneChunkloader(event.getPlayer()).playerHoldLoader();
    }
}
