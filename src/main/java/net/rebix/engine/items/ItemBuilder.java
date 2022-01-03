package net.rebix.engine.items;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.rebix.engine.Main;
import net.rebix.engine.util.enums.ButtonAction;
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

public class ItemBuilder {
    
    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material, short subID){
        item = new ItemStack(material, 1, subID);
        itemMeta = item.getItemMeta();
    }


    public ItemBuilder(Material material, String id) {
        this(material, (short)0);
        setID(id);
    }

    public ItemBuilder(Material material) {
        this(material, (short)0);
        setID("");
    }

    public ItemBuilder(ItemStack itemBuilder) {
        if(itemBuilder != null)
        item = itemBuilder;
        else {
            item = new ItemBuilder(Material.STONE).build();
        }
        itemMeta = item.getItemMeta();

    }


    public ItemBuilder setName(String $name) {
        itemMeta.setDisplayName($name);
        return this;

    }
    public ItemBuilder setAmount(int $amount) {
        item.setAmount($amount);
        return this;

    }
    public ItemBuilder setLore(String... $lore) {
        if($lore != null) {
            itemMeta.setLore(Arrays.asList($lore));

        }
        return this;
    }



    public ItemBuilder addItemFlag(ItemFlag $flag){
        itemMeta.addItemFlags($flag);

        return this;
    }
    public ItemBuilder setUnbreakable(boolean $unbreakalble){
        itemMeta.setUnbreakable($unbreakalble);
        return this;
    }
    public ItemBuilder setLocalName(String $name){
        itemMeta.setLocalizedName($name);
        return this;
    }
    public ItemBuilder setGlowing(boolean $glowing){
        if($glowing){
            itemMeta.addEnchant(Enchantment.LUCK,1,true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        return this;
    }

    public ItemBuilder skull(String value){


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

    public ItemBuilder setPickupabel(boolean pickupabel){
        String value = String.valueOf(pickupabel);
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.plugin,"Pickupabel"), PersistentDataType.STRING, value);
        return this;
    }

    public ItemBuilder setButtonAction(ButtonAction action){
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.plugin,"ButtonAction"), PersistentDataType.STRING, String.valueOf(action));
        return this;
    }

    public ItemStack build(){
        item.setItemMeta(itemMeta);
        return item;
    }

    private void setID(String id){
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.plugin, "ID"), PersistentDataType.STRING, id);
    }

    public String getID(){
        if(itemMeta != null) return itemMeta.getPersistentDataContainer().get(new NamespacedKey(Main.plugin, "ID"), PersistentDataType.STRING);
        else return null;
    }

}
