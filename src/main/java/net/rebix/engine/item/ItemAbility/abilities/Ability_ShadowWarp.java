package net.rebix.engine.item.ItemAbility.abilities;

import net.rebix.engine.Main;
import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.util.enums.ItemAbilityType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class Ability_ShadowWarp extends ItemAbility {
    public Ability_ShadowWarp() {
        super(ItemAbilityType.RIGHT_CLICK, "Shadow Warp", Arrays.asList("Teleport behind enemies", "and hit them with this weapon"), "SHADOW_WARP", 20*5f);
    }

    List<EntityType> types = Arrays.asList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.SPIDER);
    int i = 0;
    @Override
    public void use(Player player, EngineItem item) {
        if (player.getNearbyEntities(50, 50, 50).size() > 0) {
            Location player_return = player.getLocation();

        List<Entity> nearby = player.getNearbyEntities(50, 50, 50);

                nearby.removeIf(e -> !types.contains(e.getType()));
            if (nearby.size() > 0) {
                new Thread(() ->
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (i >= nearby.size()) {
                                    player.teleport(player_return);
                                    i = 0;
                                    cancel();
                                } else {


                                    Entity e = nearby.get(i);
                                    drawLine(player.getLocation(), e.getLocation(),0.1D, nearby.size());
                                    player.teleport(e.getLocation().add(e.getLocation().getDirection().multiply(-2)));
                                    player.attack(e);
                                    i++;
                                }
                            }
                        }.runTaskTimer(Main.plugin, 0L, 1L)
                ).start();
            }
    }
    }

    public void drawLine(Location point1, Location point2, double space, Integer time) {
        World world = point1.getWorld();
        double distance = point1.distance(point2);
        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0;
        for (; length < distance; p1.add(vector)) {
                   world.spawnParticle(Particle.DRIP_LAVA,p1.toLocation(world),10,0,.5,0,0);
            length += space;
        }
    }

}
