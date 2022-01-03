package net.rebix.engine.api.property;

import net.rebix.engine.Main;
import net.rebix.engine.util.enums.ButtonAction;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ItemProperties {
    private String properties;
    private ItemStack itemStack;
    public ItemProperties(ItemStack itemStack){
            this.itemStack = itemStack;
    }

    public Boolean getCannotBePickedUp(){
        return Objects.equals(itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.plugin, "Pickupabel"), PersistentDataType.STRING), "false");
    }

    public ButtonAction getButtonAction(){
        if(itemStack.getItemMeta() != null){
            String string = itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.plugin, "ButtonAction"), PersistentDataType.STRING);
            if(string != null) {
                ButtonAction action = ButtonAction.valueOf(string);
                if (action != null) return action;
                else return ButtonAction.NONE;
            } else return ButtonAction.NONE;
        } else return ButtonAction.NONE;
    }

}
