package net.rebix.engine.combat;

import net.rebix.engine.EPlayer;
import net.rebix.engine.api.stuff.WaveGenerator;
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

            switch (event.getCause()) {
                case FALL:
                    if (player.getFallDistance() < 10) {
                        event.setCancelled(true);
                    }
                    if (player.getFallDistance() > 25)
                        new WaveGenerator().generateShockWave(3, false, player.getLocation(), 10L, false, player, 0.2f, "playerland");
                    break;
                case ENTITY_EXPLOSION:
                case BLOCK_EXPLOSION:
                    event.setCancelled(true);
                    break;

            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            EPlayer player = EPlayer.get((Player) event.getEntity());
            event.setDamage(0);
        }
        if (event.getDamager() instanceof Player) {
            EPlayer player = EPlayer.get((Player) event.getDamager());

            //new FloatingText(ChatColor.RED + "" + player.getEffectiveStats().getStat(StatType.DAMAGE), event.getEntity().getLocation(), 100L, 0.1D);
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
