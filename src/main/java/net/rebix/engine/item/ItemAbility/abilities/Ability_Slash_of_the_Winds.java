package net.rebix.engine.item.ItemAbility.abilities;

import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.ItemAbility.ItemAbilityType;
import org.bukkit.entity.Player;

import java.util.Arrays;


public class Ability_Slash_of_the_Winds extends ItemAbility {




    public Ability_Slash_of_the_Winds() {
        super(ItemAbilityType.LEFT_CLICK, "§cSlash §1of §athe §fWinds", Arrays.asList("§2This ability makes a cut go threw the air.","§2Depending on your wind selected,","§2the damage area and slash range is doubled"), "SLASH_OF_THE_FOUR_WINDS");
    }



    @Override
    public boolean use(Player player, EItem engineItem) {
        return true;
    }
}
