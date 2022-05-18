package net.rebix.engine.item.items;

import net.rebix.engine.item.EngineItem;
import org.bukkit.Material;


public class TestItem extends EngineItem {
    public TestItem() {
        super("ENGINE.TESTITEM", "§c§bTestItem", Material.BEDROCK, "§f this is a test item");
    }
}
