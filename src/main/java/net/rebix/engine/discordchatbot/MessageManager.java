package net.rebix.engine.discordchatbot;

import net.rebix.engine.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageManager {

    public static HashMap<Player, Integer> PlayerChannelID = new HashMap<>();

    public void received(String message) {
        Main.plugin.getLogger().info("Message received: " + message);
        String[] split = message.split("%%split%%");
        String p = null;
        for (OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()) {
            if(player.getName().equals(split[1])) p = player.getName();
        }
        if(p == null) {
            Main.plugin.getLogger().info("Player: " + split[1] + " not found");
            return;
        }
        int channelID = Integer.parseInt(split[0]);
        String finalmessage = p+ " §b✑§r " + split[2];

        for(Player player : Bukkit.getOnlinePlayers()) {
            if(PlayerChannelID.get(player) == channelID) player.sendMessage(finalmessage);
        }
        Main.CHATBOT.sendMessage(message);
    }
}
