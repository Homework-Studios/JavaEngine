package net.rebix.engine.events.player;

import net.rebix.engine.Main;
import net.rebix.engine.discordchatbot.MessageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onPlayerChatEvent implements Listener {
    @EventHandler
    public void PlayerChatEvent(AsyncPlayerChatEvent event) {
        event.setMessage(event.getMessage().replaceAll("&&","§"));
        Main.CHATBOT.receiveMessage(MessageManager.PlayerChannelID.get(event.getPlayer()) + "%%split%%" + event.getPlayer().getName() + "%split%%" + event.getMessage());
        if(Main.plugin.getConfig().getBoolean("ChatBot"))
        event.setCancelled(true);
    }
}
