package net.rebix.engine.events.player;

import net.rebix.engine.Main;
import net.rebix.engine.api.Translator;
import net.rebix.engine.api.playernametag.PlayerSetNameTag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collections;

public class onPlayerLeaveEvent implements Listener {
    @EventHandler
    public void PlayerLeaveEvent(PlayerQuitEvent event){
        if(Main.plugin.getConfig().getBoolean("Nametag"))
        new PlayerSetNameTag(event.getPlayer(), Collections.singletonList(event.getPlayer().getDisplayName())).RemoveNameTag(event.getPlayer());
        event.setQuitMessage(new Translator().Translate("engine.player.leave").replaceAll("_"," ").replace("%%player%%",event.getPlayer().getDisplayName()));
    }
}
