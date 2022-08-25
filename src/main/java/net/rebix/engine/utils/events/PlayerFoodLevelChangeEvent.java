package net.rebix.engine.utils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodLevelChangeEvent implements Listener {

    @EventHandler
    public void onPlayerFoodLevelChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(event.getFoodLevel() + 1);
    }
}
