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


public class Ability_Dash_of_the_Winds extends ItemAbility {
    public Ability_Dash_of_the_Winds() {
        super(ItemAbilityType.SHIFT_LEFT_CLICK, "§cDash §1of §athe §fWinds", Arrays.asList("§2you are moving as fast as the wind to defeat your enemies","§2by attacking them from behind in a radius of 12 blocks","§2the effect is limited to 10 enemies"), "DASH_OF_THE_FOUR_WINDS",400f);
    }




    List<EntityType> types = Arrays.asList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.SPIDER);
    int i = 0;
    @Override
    public void use(Player player, EngineItem engineItem) {
        if (player.getNearbyEntities(12, 12, 12).size() > 0) {
            Location player_return = player.getLocation();
            List<Entity> nearby = player.getNearbyEntities(12, 12, 12);
            nearby.removeIf(e -> !types.contains(e.getType()));
            if (nearby.size() > 0) {
                new Thread(() ->
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (i >= nearby.size() || i == 10) {
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
                        }.runTaskTimer(Main.plugin, 0L, 5L)
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
            world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE,p1.toLocation(world),10,0,.5,0,0);
            length += space;
        }
    }
}
