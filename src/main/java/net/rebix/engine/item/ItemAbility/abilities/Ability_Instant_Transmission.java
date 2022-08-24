package net.rebix.engine.item.ItemAbility.abilities;

import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.ItemAbility.ItemAbilityType;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ability_Instant_Transmission extends ItemAbility {
    public Ability_Instant_Transmission() {
        super(ItemAbilityType.RIGHT_CLICK, "Instant Transmission", Arrays.asList("§7Instantly Teleports you 12 blocks ahead"), "INSTANT_TRANSMISSION");
    }

    @Override
    public boolean use(Player player, EItem engineItem) {
        Location newLocation = player.getLocation().add(player.getLocation().getDirection().multiply(12));
        if(newLocation.getBlock().getType().isSolid()|| newLocation.add(0,1,0).getBlock().getType().isSolid()) {
            return false;
        }
        player.teleport(newLocation);
        player.setVelocity(player.getLocation().getDirection().multiply(0));
        return true;
    }
}
