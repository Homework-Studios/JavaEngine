package net.rebix.engine.util.enums;

import org.bukkit.event.block.Action;

public enum ItemAbilityType {
    RIGHT_CLICK,
    LEFT_CLICK,
    SHIFT_RIGHT_CLICK,
    SHIFT_LEFT_CLICK;


    public static ItemAbilityType translateFromInteractEvent(Action action, boolean shift) {
        switch (action) {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                if (shift)
                return SHIFT_LEFT_CLICK;
                else return LEFT_CLICK;
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                if (shift)
                return SHIFT_RIGHT_CLICK;
                else return RIGHT_CLICK;
        }
        return null;
    }
}

