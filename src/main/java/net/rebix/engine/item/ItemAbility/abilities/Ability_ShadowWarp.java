package net.rebix.engine.item.ItemAbility.abilities;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.util.enums.ItemAbilityType;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Ability_ShadowWarp extends ItemAbility {
    public Ability_ShadowWarp() {
        super(ItemAbilityType.RIGHT_CLICK, "Shadow Warp", "Teleport behind %%amount%% enemies and hit them\nwith this weapon", "SHADOW_WARP", 20*5f);
    }

    List<EntityType> types = Arrays.asList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.SPIDER);

    @Override
    public void use(Player player, EngineItem item) {
        if (player.getNearbyEntities(20, 20, 20).size() > 0) {
            Location player_return = player.getLocation();
        player.getNearbyEntities(20, 20, 20).forEach(e -> {
            if (types.contains(e.getType())) {
                player.teleport(e.getLocation().add(e.getLocation().getDirection().multiply(-2)));
                player.attack(e);
            }
        });
        player.teleport(player_return);
    }
    }
}
