package net.rebix.engine.util;

import net.rebix.engine.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class TickingTask10timesperseckond {

    public TickingTask10timesperseckond() {
        new Thread(() -> Bukkit.getScheduler().runTaskTimer(Main.plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.isSneaking() && player.isGliding() && player.getGameMode() == GameMode.CREATIVE) {
                    player.setVelocity(player.getEyeLocation().getDirection().multiply(3));
                    player.getWorld().spawnParticle(Particle.CLOUD,player.getLocation(), 30, 0, 0, 0, .2);
                }
            }

        }, 0L, 2L)).start();


    }

}
