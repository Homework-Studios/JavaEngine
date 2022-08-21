package net.rebix.engine.menus.menus;

import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemStackBuilder;
import net.rebix.engine.item.modifier.Modifier;
import net.rebix.engine.menus.Menu;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InspectorMenu extends Menu {

    EItem eItem;

    public InspectorMenu(String name, int size, Inventory parent, EItem item) {
        super(name, size, parent);
        this.eItem = item;
    }


    @Override
    public boolean onUpdate() {
        ItemStack item = new ItemStackBuilder(eItem.getItem()).setPickupabel(false).setLocalName("").build();
        items.set(13,item);
        items.set(29,new ItemStackBuilder(eItem.getModifiers().get(0).getItem()).setPickupabel(false).build());
        items.set(31,Modifier.getEmptyItem(eItem.getLevel(), 2));
        items.set(33,Modifier.getEmptyItem(eItem.getLevel(), 3));
        return true;
    }
}
