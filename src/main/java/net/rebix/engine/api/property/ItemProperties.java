package net.rebix.engine.api.property;

import net.rebix.engine.Main;
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
        if(itemStack.getItemMeta() != null)
        return Objects.equals(itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.plugin, "Pickupabel"), PersistentDataType.STRING), "false");
        else return false;
    }

    public String getButtonAction() {
        if (itemStack.getItemMeta() != null) {
            String string = itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.plugin, "ButtonAction"), PersistentDataType.STRING);
            if (string != null) {
                return string;
            } return null;
        } return null;
    }

}
