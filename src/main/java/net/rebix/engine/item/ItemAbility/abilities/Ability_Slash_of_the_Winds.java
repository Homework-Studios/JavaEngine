package net.rebix.engine.item.ItemAbility.abilities;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.util.enums.ItemAbilityType;
import net.rebix.engine.util.enums.SkyDirection;
import org.bukkit.entity.Player;

import java.util.Arrays;


public class Ability_Slash_of_the_Winds extends ItemAbility {




    public Ability_Slash_of_the_Winds() {
        super(ItemAbilityType.LEFT_CLICK, "§cSlash §1of §athe §fWinds", Arrays.asList("§2This ability makes a cut go threw the air.","§2Depending on your wind selected,","§2the damage area and slash range is doubled"), "SLASH_OF_THE_FOUR_WINDS");
    }



    SkyDirection getDirection(float yaw) {
    if(yaw <= -135 || yaw >= 135)
        return SkyDirection.NORTH;
    if(yaw < 135 && yaw > 45)
        return SkyDirection.WEST;
    if(yaw >= -45)
        return SkyDirection.SOUTH;
    if(yaw > -135)
        return SkyDirection.EAST;
return SkyDirection.NONE;
    }


    @Override
    public void use(Player player, EngineItem engineItem) {
        SkyDirection facing = getDirection(player.getLocation().getYaw());
        float yaw = facing.yaw;

    }
}
