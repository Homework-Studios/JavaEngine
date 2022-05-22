package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemAbility.abilities.Ability_Nuclear_blast;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_ATOM_BOMB extends EngineItem {
    public ENGINE_ITEM_ATOM_BOMB() {
        super(new ItemBuilder(Material.TNT,"ENGINE_ITEM:ATOM_BOMB").setName("§4§lATOM BOMBE").setLore("§cWTF BOOM").setGlowing(true));
    }

    @Override
    public void init() {
        addAbility(new Ability_Nuclear_blast());
    }
}
