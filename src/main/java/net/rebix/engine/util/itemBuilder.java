package net.rebix.engine.util;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.rebix.engine.Main;
import net.rebix.engine.util.api.inventorycomponents.ButtonAction;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class itemBuilder {
    private ItemStack item;
    private ItemMeta itemMeta;





    public itemBuilder(Material material, short subID){
        item = new ItemStack(material, 1, subID);
        itemMeta = item.getItemMeta();
    }

    public itemBuilder(Material material) {
        this(material, (short)0);
    }


    public itemBuilder setName(String $name) {
        itemMeta.setDisplayName($name);
        return this;

    }
    public itemBuilder setAmount(int $amount) {
        item.setAmount($amount);
        return this;

    }
    public itemBuilder setLore(String... $lore) {
        if($lore != null) {
            itemMeta.setLore(Arrays.asList($lore));

        }
        return this;
    }



    public itemBuilder additemflag(ItemFlag $flag){
        itemMeta.addItemFlags($flag);

        return this;
    }
    public itemBuilder setunbreakable(boolean $unbreakalble){
        itemMeta.setUnbreakable($unbreakalble);
        return this;
    }
    public itemBuilder setlocalname(String $name){
        itemMeta.setLocalizedName($name);
        return this;
    }
    public itemBuilder setglowing(boolean $glowing){
        if($glowing){
            itemMeta.addEnchant(Enchantment.LUCK,1,true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        return this;
    }

    public itemBuilder skull(String value){


        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures",new Property("textures",value));
        Field profileField;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, gameProfile);
        } catch (Exception ignored) {
        }

        itemMeta = skullMeta;

        return this;
    }

    public itemBuilder setPickupabel(boolean pickupabel){
        String value = String.valueOf(pickupabel);
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.plugin,"Pickupabel"), PersistentDataType.STRING, value);
        return this;
    }

    public itemBuilder setButtonAction(ButtonAction action){
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.plugin,"ButtonAction"), PersistentDataType.STRING, String.valueOf(action));
        return this;
    }


    public ItemStack build(){
        item.setItemMeta(itemMeta);
        return item;
    }
}
