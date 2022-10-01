package net.rebix.engine.utils.customevents;


import net.rebix.engine.api.stuff.WaveGenerator;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EntityShockWavehitEvent extends Event {


    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Location location;
    private final WaveGenerator waveGenerator;
    private final Entity entity;

    public EntityShockWavehitEvent(Player player, Location location, Entity entity, WaveGenerator generator) {
        this.player = player;
        this.location = location;
        this.waveGenerator = generator;
        this.entity = entity;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Location getLocation() {
        return location;
    }


    public Entity getEntity() {
        return entity;
    }

    public WaveGenerator getWaveGenerator() {
        return waveGenerator;
    }
}
