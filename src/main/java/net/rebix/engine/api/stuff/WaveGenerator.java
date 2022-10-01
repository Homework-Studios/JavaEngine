package net.rebix.engine.api.stuff;

import net.rebix.engine.EPlayer;
import net.rebix.engine.JavaEngine;
import net.rebix.engine.utils.customevents.EntityShockWavehitEvent;
import net.rebix.engine.utils.variables.Vector2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class WaveGenerator {

    public int i;
    public Player player;
    public String id = "";

    boolean event;

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

    public void generateShockWave(int radius, boolean event, Location location, Long bps, boolean inverted, @Nullable EPlayer player, float v, String id) {
        location.setX(Math.floor(location.getX()) + 0.5);
        location.setY(Math.floor(location.getY()));
        location.setZ(Math.floor(location.getZ()) + 0.5);
        player = player == null ? null : EPlayer.get(player);
        this.id = id == null ? "" : id;

        waveStep(radius * 2, event, location, bps, inverted, player, v);
    }

    void waveStep(int radius, boolean event, Location location, Long bps, boolean inverted, @Nullable EPlayer player, float v) {
        this.event = event;
        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaEngine.plugin, () -> {
            if (inverted) {
                if (i == 0) {
                    i = radius;
                } else {
                    i--;
                }
                shockWaveSpawn(location, WaveGenerator.getCircle(i), v);

                if (i != 0) waveStep(radius, event, location, bps, inverted, player, v);
            } else if (i < radius) {
                i++;
                shockWaveSpawn(location, WaveGenerator.getCircle(i), v);
                waveStep(radius, event, location, bps, inverted, player, v);
            } else i = 0;
        }, 20 / bps);
    }

    List<Entity> shockWaveSpawn(Location l, List<Vector2> cords, float offset) {
        for (Vector2 cord : cords) {

            Location location = new Location(l.getWorld(), l.getX() + cord.getX(), l.getY(), l.getZ() + cord.getY());
            if (event) for (Entity e : location.getChunk().getEntities()) {
                if (e.getLocation().distance(location) < 1) {
                    Bukkit.getPluginManager().callEvent(new EntityShockWavehitEvent(player, l, e, this));
                }
            }
            Material material = location.subtract(0, 1, 0).getBlock().getType();
            if (location.getBlock().getType().isSolid()) {
                FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(location, Material.COBBLESTONE, (byte) 0);
                fallingBlock.setDropItem(false);
                fallingBlock.setHurtEntities(false);
                fallingBlock.setGravity(false);
                fallingBlock.setInvulnerable(true);

                WaveAnimation(fallingBlock, fallingBlock.getLocation().getBlockY(), true, offset);
            }

        }
        return null;
    }

    void WaveAnimation(FallingBlock block, float startY, boolean up, float offset) {
        if (offset < .9f) {

        } else offset = 0.8999f;
        float finalOffset = offset;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaEngine.plugin, () -> {
            if (up) {
                if (block.getLocation().getY() < startY + finalOffset) {
                    block.setVelocity(new Vector(0, 0.1, 0));
                } else {
                    WaveAnimation(block, startY - finalOffset, false, finalOffset);
                }
            } else {
                if (block.getLocation().getY() > startY) {
                    block.setVelocity(new Vector(0, -0.1, 0));
                } else {
                    block.remove();
                }
            }
        }, 0, 1);


    }

}
