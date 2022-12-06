package net.rebix.engine.api;

import net.rebix.engine.JavaEngine;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

public class FloatingText {

    private Double floatingDirection = 0D;
    private String text;
    private Long lifetime = 20L;
    private Location location;
    private ArmorStand entity;

    public FloatingText(String text, Location location) {
        this.text = text;
        this.location = location;

        spawn();
    }

    public FloatingText(String text, Location location, Long lifetime, Double floatingDirection) {
        this.text = text;
        this.location = location;
        this.lifetime = lifetime;
        this.floatingDirection = floatingDirection;

        spawn();
    }

    public FloatingText(String text, Location location, Long lifetime) {
        this.text = text;
        this.location = location;
        this.lifetime = lifetime;

        spawn();
    }

    public FloatingText(String text, Location location, Double floatingDirection) {
        this.text = text;
        this.location = location;
        this.floatingDirection = floatingDirection;

        spawn();
    }


    void spawn() {
        //make Entity
        entity = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        entity.teleport(new Location(location.getWorld(), 0, 0, 0, 0, 0));
        entity.setInvisible(true);
        entity.setMarker(true);
        entity.setCustomName(text);
        entity.setCustomNameVisible(true);
        entity.addScoreboardTag("RemoveEntityOnDisable");

        //destroy Delay
        if (lifetime != null) new BukkitRunnable() {
            @Override
            public void run() {
                destroy();
            }
        }.runTaskLater(JavaEngine.plugin, lifetime);

        //movement
        new BukkitRunnable() {
            @Override
            public void run() {
                Location location1 = new Location(location.getWorld(), location.getX(), location.getY() + floatingDirection, location.getZ());
                entity.teleport(location1);
                location = location1;
                if (entity == null) cancel();
            }
        }.runTaskTimer(JavaEngine.plugin, 0L, 0L);
    }

    public void destroy() {
        entity.remove();
    }

    //Getter / Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLifetime() {
        return lifetime;
    }

    public void setLifetime(Long lifetime) {
        this.lifetime = lifetime;
    }

    public Double getFloatingDirection() {
        return floatingDirection;
    }

    public void setFloatingDirection(Double floatingDirection) {
        this.floatingDirection = floatingDirection;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArmorStand getEntity() {
        return entity;
    }
}
