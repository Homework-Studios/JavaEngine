package net.rebix.engine.item;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.rebix.engine.JavaEngine;
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
import java.util.List;
import java.util.UUID;

public class ItemStackBuilder {
    
    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemStackBuilder(Material material, short subID){
        item = new ItemStack(material, 1, subID);
        itemMeta = item.getItemMeta();
        
    }


    public ItemStackBuilder(Material material, String id) {
        this(material, (short)0);
        setID(id);
    }

    public ItemStackBuilder(Material material) {
        this(material, (short)0);

    }

    public ItemStackBuilder(ItemStack itemBuilder) {
        if(itemBuilder != null)
        item = itemBuilder;
        else {
            item = new ItemStackBuilder(Material.STONE).build();
        }
        itemMeta = item.getItemMeta();

    }


    public ItemStackBuilder setName(String name) {
        itemMeta.setDisplayName(name);
        return this;

    }
    public ItemStackBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;

    }
    public ItemStackBuilder setLore(String... lore) {
        if(lore != null) {
            itemMeta.setLore(Arrays.asList(lore));

        }
        return this;
    }



    public ItemStackBuilder addItemFlag(ItemFlag flag){
        itemMeta.addItemFlags(flag);

        return this;
    }
    public ItemStackBuilder setUnbreakable(boolean unbreakalble){
        itemMeta.setUnbreakable(unbreakalble);
        return this;
    }
    public ItemStackBuilder setLocalName(String name){
        itemMeta.setLocalizedName(name);
        return this;
    }
    public ItemStackBuilder setGlowing(boolean glowing){
        if(glowing){
            itemMeta.addEnchant(Enchantment.LUCK,1,true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        return this;
    }

    public ItemStackBuilder skull(String value){
        item.setType(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.fromString("0ed8b527-d3cf-48a4-b9fc-c35c9efee447"), null);
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

    public ItemStackBuilder setPickupabel(boolean pickupabel){
        String value = String.valueOf(pickupabel);
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin,"pickupabel"), PersistentDataType.STRING, value);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemStackBuilder setButtonAction(String action){
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin,"ButtonAction"), PersistentDataType.STRING, String.valueOf(action));
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build(){
        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemStackBuilder setID(String id){
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "ID"), PersistentDataType.STRING, id);
        item.setItemMeta(itemMeta);
        return this;
    }

    public String getID(){
        if(itemMeta != null){
            String id =itemMeta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "ID"), PersistentDataType.STRING);
            if(id != null)
                return id;
        }
            return null;
    }

    public List<String> getLore() {
        return itemMeta.getLore();
    }

    public Material getMaterial() {
        return item.getType();
    }

    public String getName() {
        return itemMeta.getDisplayName();
    }


    public ItemStackBuilder setCustomModelData(int s) {
        itemMeta.setCustomModelData(s);
        return this;
    }
}
