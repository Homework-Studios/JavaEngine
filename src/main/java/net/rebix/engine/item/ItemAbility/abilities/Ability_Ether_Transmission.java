package net.rebix.engine.item.ItemAbility.abilities;

import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.ItemAbility.ItemAbilityType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Ability_Ether_Transmission extends ItemAbility {
    public Ability_Ether_Transmission() {
        super(ItemAbilityType.SHIFT_RIGHT_CLICK, "Ether Transmission", Arrays.asList("§7Instantly Teleports you where you are looking", "§7The maximum range is 100 blocks"), "ENGINE_ABILITY_ETHER_TRANSMISSION");
    }

    @Override
    public boolean use(Player player, EItem engineItem) {
        Location newLocation = player.getTargetBlock(null, 100).getLocation();
        if(newLocation.add(0,2,0).getBlock().getType().isSolid()|| newLocation.add(0,1,0).getBlock().getType().isSolid()) {
            return false;
        }
        player.teleport(newLocation);
        return true;
    }
}
