package net.rebix.engine.utils.events;

import net.rebix.engine.EPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        new EPlayer(event.getPlayer());
    }
}
