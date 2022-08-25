package net.rebix.engine.combat;

import net.rebix.engine.EPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DamageEvents implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            EPlayer player = EPlayer.get((Player) event.getEntity());
            event.setDamage(0);

        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            EPlayer player = EPlayer.get((Player) event.getEntity());
            event.setDamage(0);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByBlockEvent event) {
        if (event.getEntity() instanceof Player) {
            EPlayer player = EPlayer.get((Player) event.getEntity());
            event.setDamage(0);
        }
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        EPlayer player = EPlayer.get(event.getPlayer());
        player.respawn();
    }
}
