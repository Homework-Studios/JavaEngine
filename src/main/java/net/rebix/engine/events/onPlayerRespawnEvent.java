package net.rebix.engine.events;

import net.rebix.engine.api.playernametag.PlayerSetNameTag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Collections;

public class onPlayerRespawnEvent implements Listener {
    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event){
    new PlayerSetNameTag(event.getPlayer(), Collections.singletonList(event.getPlayer().getDisplayName()));
    }
}
