package net.rebix.engine.util.api;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.rebix.engine.util.itemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class PlayerHead {
    private ItemStack skull;

    public PlayerHead(String id) {
        skull = new itemBuilder(Material.PLAYER_HEAD).build();
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures",new Property("textures",id));
        Field profileField;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, gameProfile);
        } catch (Exception ignored) {
        }

        skull.setItemMeta(skullMeta);
    }

    public ItemStack getItem(){
        return skull;
    }
}
