package net.rebix.engine.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onPlayerChatEvent implements Listener {
    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent event) {
        event.setMessage(event.getMessage().replaceAll("&&","§"));
    }
}
