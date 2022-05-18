package net.rebix.engine.item.items.engineitems;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemAbility.abilities.Ability_ShadowWarp;
import net.rebix.engine.item.ItemBuilder;
import org.bukkit.Material;

public class ENGINE_ITEM_TEST_FURY extends EngineItem {
    public ENGINE_ITEM_TEST_FURY() {
        super(new ItemBuilder(Material.DIAMOND_SWORD, "ENGINE_ITEM:TEST_FURY").setName("§8Test Fury").build());
    }

    @Override
    public void init() {
        addAbility(new Ability_ShadowWarp());
        updateLore();
    }
}
