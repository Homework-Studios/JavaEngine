package net.rebix.engine.utils.customevents;


import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.ItemAbilityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ItemUseAbilityEvent extends Event {


    private final Player player;
    private final EItem item;
    private final ItemAbilityType type;

    public ItemUseAbilityEvent(Player player, EItem item, ItemAbilityType type) {
        this.player = player;
        this.item = item;
        this.type = type;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return this.player;
    }

    public EItem getItem() {
        return item;
    }

    public ItemAbilityType getTrigger() {
        return type;
    }
}
