package net.rebix.engine.util;



import net.rebix.engine.item.ItemFactory;
import org.bukkit.inventory.Inventory;

public class FillInventoryWithPlaceholder {
    public FillInventoryWithPlaceholder(Inventory $inventory){
        int $index;
        for($index = 0; $index < $inventory.getSize(); ++$index){
            if($inventory.getItem($index) == null) {
                $inventory.setItem($index, ItemFactory.Items.get("PLACEHOLDER"));
            }
        }
    }
}
