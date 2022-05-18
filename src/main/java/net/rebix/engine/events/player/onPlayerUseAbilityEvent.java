package net.rebix.engine.events.player;

import net.rebix.engine.events.customevents.ItemUseAbilityEvent;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onPlayerUseAbilityEvent implements Listener {

    @EventHandler
    public void PlayerUseAbilityEvent(ItemUseAbilityEvent event) {
        for (ItemAbility itemAbility : event.getItem().getAbilities()) {
            itemAbility.use(event.getPlayer(), event.getItem());
        }
    }
}
