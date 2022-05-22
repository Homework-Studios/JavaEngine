package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemAbility.abilities.Ability_Dash_of_the_Winds;
import net.rebix.engine.item.ItemAbility.abilities.Ability_Slash_of_the_Winds;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_SWORD_OF_THE_FOUR_WINDS extends EngineItem {
    public ENGINE_ITEM_SWORD_OF_THE_FOUR_WINDS() {
        super(new ItemBuilder(Material.DIAMOND_SWORD,"ENGINE_ITEM:ENGINE_ITEM_SWORD_OF_THE_FOUR_WINDS").setName("§cSword §1of the §aFour §fWinds").setLore("§7§oThe Sword was forged by the Draws","§7§oThe Dwarfs lived in the","§c§oNorth§7§o,§1§oEast§7§o,§a§oSouth§7§o,§f§oWest§7§o winds").setCustomModelData(111111).setGlowing(true));
    }

    @Override
    public void init() {
        addAbility(new Ability_Slash_of_the_Winds());
        addAbility(new Ability_Dash_of_the_Winds());
    }
}
