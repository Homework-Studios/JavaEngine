package net.rebix.engine.item.items;

import net.rebix.engine.item.EngineItem;
import org.bukkit.Material;

import java.util.Collections;


public class TestItem extends EngineItem {
    public TestItem() {
        super("ENGINE.TESTITEM", "§c§bTestItem", Collections.singletonList("§fThis is A Test Item"), Material.BEDROCK);
    }
}
