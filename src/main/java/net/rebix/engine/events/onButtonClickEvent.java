package net.rebix.engine.events;

import net.rebix.engine.events.customevents.ButtonClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onButtonClickEvent implements Listener {
    @EventHandler
    public void ButtonClickEvent(ButtonClickEvent event) {
        switch (event.getButtonAction()) {
            case "BUTTON.ACTION.EXIT":
                event.getPlayer().closeInventory();
                break;
        }
    }
}
