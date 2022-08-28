package net.rebix.engine.combat;

import net.rebix.engine.EPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

public class onPlayerToggleSneakEvent implements Listener {
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking()) {
            EPlayer player = EPlayer.get(event.getPlayer());
            player.setVelocity(player.getLocation().getDirection().multiply(2));
            player.setFallDistance(0);
        }
    }

    @EventHandler
    public void PlayerToggleFlightEvent(PlayerToggleFlightEvent event) {
        if (event.isFlying()) {
            EPlayer player = EPlayer.get(event.getPlayer());
            player.setVelocity(player.getVelocity().add(new Vector(0, 1.5, 0).add(player.getLocation().getDirection().multiply(1.5).setY(0))));
            player.setFallDistance(0);
            event.setCancelled(true);
        }
    }
}
