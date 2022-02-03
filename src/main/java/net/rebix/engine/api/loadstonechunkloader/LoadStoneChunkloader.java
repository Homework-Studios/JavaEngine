package net.rebix.engine.api.loadstonechunkloader;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.rebix.engine.Main;
import net.rebix.engine.api.Translator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LoadStoneChunkloader {

    private Player player;

    public LoadStoneChunkloader(Player player) {
        this.player = player;
    }

    public void loaderPlaced(Location location) {
        location.getChunk().setForceLoaded(true);
    }

    public void loaderBreak(Location location) {
        location.getChunk().setForceLoaded(false);
    }

    public void playerHoldLoader() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(player.getLocation().getChunk().isForceLoaded())
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(new Translator().Translate("engine.chunkload.loadet")));
                else player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(new Translator().Translate("engine.chunkload.notloadet")));

                if (player.getItemInUse().getType() != Material.LODESTONE) cancel();
            }
        }.runTaskLaterAsynchronously(Main.plugin,10L);
    }
}
