package net.rebix.engine.events.customevents;

import net.rebix.engine.api.inventorycomponents.ButtonAction;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ButtonClickEvent extends Event {

    private final Player player;
    private final ButtonAction buttonAction;

    public ButtonClickEvent(Player player, ButtonAction buttonAction) {
        this.player = player;
        this.buttonAction = buttonAction;
    }

    private static final HandlerList HANDLERS = new HandlerList();

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

    public ButtonAction getButtonAction() {
        return buttonAction;
    }
}
