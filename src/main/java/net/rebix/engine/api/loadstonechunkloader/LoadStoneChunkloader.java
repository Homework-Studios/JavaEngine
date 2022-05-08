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

    private final Player player;

    public LoadStoneChunkloader(Player player) {
        this.player = player;
    }

    public void loaderPlaced(Location location) {
        player.sendMessage(new Translator().Translate("engine.chunkload.nowloadet"));
        location.getChunk().setForceLoaded(true);

    }

    public void loaderBreak(Location location) {
        player.sendMessage(new Translator().Translate("engine.chunkload.nownotloadet"));
        location.getChunk().setForceLoaded(false);

    }

    public void playerHoldLoader() {
        if(player.getLocation().getChunk().isForceLoaded())
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(new Translator().Translate("engine.chunkload.loadet")));
        else player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(new Translator().Translate("engine.chunkload.notloadet")));

        new BukkitRunnable() {
            @Override
            public void run() {

                if(player.getInventory().getItemInMainHand() != null)
                    if(player.getInventory().getItemInMainHand().getType() == Material.LODESTONE)
                playerHoldLoader();
            }
        }.runTaskLaterAsynchronously(Main.plugin,5L);

    }
}
