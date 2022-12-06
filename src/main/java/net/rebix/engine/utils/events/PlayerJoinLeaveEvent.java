package net.rebix.engine.utils.events;

import net.minecraft.network.protocol.game.PacketPlayOutKeepAlive;
import net.rebix.engine.EPlayer;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinLeaveEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        new EPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(org.bukkit.event.player.PlayerQuitEvent event) {
        if (!EPlayer.get(event.getPlayer()).onLeave())
            ((CraftPlayer) event.getPlayer()).getHandle().b.a(new PacketPlayOutKeepAlive(0));
    }
}
