package net.rebix.engine.item.ItemAbility.abilities;

import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.ItemAbility.ItemAbilityType;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Ability_Nuclear_blast extends ItemAbility {
    public Ability_Nuclear_blast() {
        super(ItemAbilityType.RIGHT_CLICK, "Nuclear Blastoff", Arrays.asList("§7This thing is just going","§4§bBOOM"), "ENGINE_ABILITY_NUCLEAR_BLASTOFF");
    }

    @Override
    public boolean use(Player player, EItem engineItem) {
        player.getWorld().createExplosion(player.getLocation(), 10,false, false);
        return true;
    }
}
