package net.rebix.engine.item.ItemAbility.abilities;

import net.rebix.engine.EPlayer;
import net.rebix.engine.JavaEngine;
import net.rebix.engine.api.stuff.WaveGenerator;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.ItemAbility.ItemAbilityType;
import net.rebix.engine.utils.customevents.EntityShockWavehitEvent;
import org.bukkit.Location;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

public class ItemAbilityGravityStrom extends ItemAbility implements Listener {
    int ticks = 0;

    public ItemAbilityGravityStrom() {
        super(ItemAbilityType.RIGHT_CLICK, "§5Gravity Storm", Collections.singletonList("§7pulls all enemies in a 5 block to it selves"), "GRAVITY_STORM");
    }

    @Override
    public boolean use(Player player, EItem item) {
        Location location = player.getLocation();
        ticks = 0;

        new BukkitRunnable() {
            @Override
            public void run() {
                new WaveGenerator().generateShockWave(5, true, location, 10L, true, EPlayer.get(player), 0.2f, "GRAVITY_STORM");
                ticks++;
                if (ticks > 9)
                    cancel();
            }
        }.runTaskTimer(JavaEngine.plugin, 0, 10L);

        return false;
    }

    @EventHandler
    public void onEntityShockWavehitEvent(EntityShockWavehitEvent event) {
        if (event.getWaveGenerator().id.equals("GRAVITY_STORM")) {
            if (!(event.getEntity() instanceof FallingBlock)) {

                event.getEntity().teleport(event.getLocation());
            }

        }
    }

}
