package net.rebix.engine.events.customevents;

import net.rebix.engine.util.enums.ButtonAction;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryAction;
import org.jetbrains.annotations.NotNull;

public class ButtonClickEvent extends Event {

    private final Player player;
    private String action;
    private String buttonAction;

    public ButtonClickEvent(Player player, InventoryAction action, String buttonAction) {
        this.player = player;
        this.action = action.toString();
        this.buttonAction = buttonAction;
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
    
    public void setButtonAction(String action){
        this.buttonAction = action;
    }
    public void setButtonAction(ButtonAction action){
        this.buttonAction = action.toString();
    }

    public String getButtonAction() {
        return buttonAction;
    }
    public InventoryAction getButtonActionE() {
        return InventoryAction.valueOf(action);
    }
}
