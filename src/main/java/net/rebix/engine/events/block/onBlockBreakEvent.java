package net.rebix.engine.events.block;

import net.rebix.engine.api.loadstonechunkloader.LoadStoneChunkloader;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class onBlockBreakEvent implements Listener {
    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.LODESTONE)) new LoadStoneChunkloader(event.getPlayer()).loaderBreak(event.getBlock().getLocation());
    }
}
