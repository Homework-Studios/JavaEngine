package net.rebix.engine.api.stuff;

import net.rebix.engine.EPlayer;
import net.rebix.engine.JavaEngine;
import net.rebix.engine.item.ItemStackBuilder;
import net.rebix.engine.utils.variables.Vector2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class WaveGenerator {


    public static List<Vector2> getCircle(int radius) {
        List<Vector2> cords = new ArrayList<>();
       //get circle around cords where z is always 0
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                    if (x * x + y * y <= radius * radius) {
                        if (x * x + y * y > (radius - 1) * (radius - 1)) {
                            cords.add(new Vector2(x, y));
                        }
                    }

            }
        }
        return cords;
    }

    public void generateShockWave(int radius, boolean event, Location location, Long bps, boolean inverted,@Nullable EPlayer player) {
        location.setX(Math.floor(location.getX()) + 0.5);
        location.setY(Math.floor(location.getY()));
        location.setZ(Math.floor(location.getZ()) + 0.5);

        waveStep(radius, event, location, bps, inverted, player);
    }
    public int i;
    void waveStep(int radius, boolean event, Location location, Long bps, boolean inverted, @Nullable EPlayer player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaEngine.plugin, () -> {
            if(inverted) {
                if(i == 0) {
                    i = radius;
                } else {
                    i--;
                }
                shockWaveSpawn(location, WaveGenerator.getCircle(i));

                if(i != 0) waveStep(radius, event, location, bps, inverted, player);
            }
            else if (i < radius) {
                i++;
                shockWaveSpawn(location, WaveGenerator.getCircle(i));
                waveStep(radius, event, location, bps, inverted, player);
            } else i = 0;
        }, 20/bps);
    }
    List<Entity> shockWaveSpawn(Location l, List<Vector2> cords) {
        for (Vector2 cord : cords) {
            Location location = new Location(l.getWorld(), l.getX() + cord.getX(), l.getY(), l.getZ() + cord.getY());
            Material material = location.subtract(0,1,0).getBlock().getType();
            if(material != Material.AIR) {
                FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(location.add(0,1,0), material, (byte) 0);
                fallingBlock.setDropItem(false);
                fallingBlock.setHurtEntities(false);
                fallingBlock.setGravity(false);

            }

        }
        return null;
    }
}
