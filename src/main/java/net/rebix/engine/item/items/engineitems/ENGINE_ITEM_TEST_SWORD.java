package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemAbility.abilities.Ability_ShadowWarp;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_TEST_SWORD extends EngineItem {
    public ENGINE_ITEM_TEST_SWORD() {
        super(new ItemBuilder(Material.POINTED_DRIPSTONE,"ENGINE_ITEM:TEST_SWORD").setName("§4§lSTECHER").setLore("§fHerzensbrecher").setUnbreakable(true).setGlowing(true));
    }

    @Override
    public void init() {
        addAbility(new Ability_ShadowWarp());
        super.init();
    }
}
