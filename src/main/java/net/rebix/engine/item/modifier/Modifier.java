package net.rebix.engine.item.modifier;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.rebix.engine.JavaEngine;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemStackBuilder;
import net.rebix.engine.item.stats.ItemStatType;
import net.rebix.engine.item.stats.ItemStats;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.*;

public class Modifier {
    private ModifierType type;
    private Modifiers modifier;
    private String id;
    private ItemStack item = new ItemStack(Material.PLAYER_HEAD);
    private ItemMeta meta;
    private ItemStatType itemStats;
    private Integer value;

    public static HashMap<String, Modifier> registered = new HashMap<>();

    public Modifier(ModifierType type,Modifiers modifier,ItemStatType statType, Integer value) {
        this.type = type;
        this.modifier = modifier;
        this.itemStats = statType;
        itemStats.setValue(value);
    }
    public Modifier(ItemStack item) {
        if(item.hasItemMeta())
            if(Objects.requireNonNull(item.getItemMeta()).getLocalizedName().contains("modifier")) {
                meta = item.getItemMeta();
                type = ModifierType.valueOf(meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "type"), PersistentDataType.STRING));
                modifier = Modifiers.valueOf(meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "modifier"), PersistentDataType.STRING));
                id = meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING);
                itemStats = ItemStatType.valueOf(meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "stat"), PersistentDataType.STRING));
                value = meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "value"), PersistentDataType.INTEGER);
                item.setItemMeta(meta);
                this.item = item;
            }
    }

    public static Modifier get(String id) {
        Modifier modifier = registered.get(id);
        ItemStatType itemStats = modifier.itemStats;
        itemStats.setValue(modifier.value);
        modifier.setItemStats(itemStats);
        return modifier;
    }

    public Modifier(File file) {
        try {
            meta = item.getItemMeta();
            JsonObject json = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
            id = file.getName().replace(".json", "");
            setItemStats(ItemStatType.valueOf(json.get("stat").getAsString()));
            setType(ModifierType.valueOf(json.get("type").getAsString()));
            setModifier(Modifiers.valueOf(json.get("modifier").getAsString()));
            String[] split = json.get("value").getAsString().split(":");
            value = Integer.parseInt(split[0]);
            itemStats = ItemStatType.valueOf(json.get("stat").getAsString());

            itemStats.setValue(value);
            setValue(itemStats.getValue());
            meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING, id);
            meta.setLocalizedName("modifier");
            meta.setDisplayName(type.getColor() + id);
            meta.setLore(Collections.singletonList(getLoreString()));
            SkullMeta skullMeta = (SkullMeta) meta;
            GameProfile gameProfile = new GameProfile(UUID.fromString("0ed8b527-d3cf-48a4-b9fc-c35c9efee447"), null);
            gameProfile.getProperties().put("textures",new Property("textures",getType().getSkull()));
            Field profileField;
            try {
                profileField = skullMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(skullMeta, gameProfile);
            } catch (Exception ignored) {
            }
            meta = skullMeta;
            item.setItemMeta(meta);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ItemStack getEmptyItem(int lvl, int slot) {
        if(lvl + 1 >= slot) return new ItemStackBuilder(Material.WHITE_STAINED_GLASS_PANE).setName("§fEmpty").setPickupabel(false).build();
        else return new ItemStackBuilder(Material.RED_STAINED_GLASS_PANE).setName("§4Locked").setPickupabel(false).build();
    }


    private void setValue(Integer valueOf) {
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "value"), PersistentDataType.INTEGER, valueOf);
        value = valueOf;
    }



    public static Modifier getbyString(String modifier) {
        return get(modifier);
    }

    public ModifierType getType() {
        return type;
    }

    public Modifiers getModifier() {
        return modifier;
    }



    public String getString() {
        return id;
    }

    public String getLore() {
        return "§f[" + getType().getColor() + "☆§f] ";
    }

    public ItemStack getItem() {
        item.setItemMeta(meta);
        return item;
    }

    void register() {
        registered.put(id, this);
    }

    public static void registerAll() {
        String parent = JavaEngine.plugin.getDataFolder().getAbsolutePath() + "/Modifier";
        List<File> files = new ArrayList<>();
        EItem.listf(parent, files);

        for(File file : files) {
            new Modifier(file).register();
        }
       // updateForPlayers();
    }

    public static void updateForPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Inventory inv = player.getInventory();
            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack item = inv.getItem(i);
                if(item == null) continue;
                if(item.getType() == Material.AIR) continue;
                if(item.getItemMeta() == null) continue;
                if(item.getItemMeta().getLocalizedName().contains("modifier")) {
                    Modifier mod = new Modifier(Objects.requireNonNull(player.getInventory().getItem(i)));
                    mod.updateItem(inv, i);
                }
            }
        }
    }

    public void updateItem(Inventory inv, int i) {
        updateValues();
        inv.setItem(i, getItem());
    }

    private void updateValues() {
        Modifier update = get(id);
        setType(update.getType());
        setModifier(update.getModifier());
        setItemStats(update.itemStats);
        meta.setLore(Collections.singletonList(getLoreString()));
        item.setItemMeta(meta);
    }

    private String getLoreString() {
        return "§f" + itemStats.name() + ":" + itemStats.getValue();
    }

    void setType(ModifierType type) {
        this.type = type;
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "type"), PersistentDataType.STRING, type.name());
    }

    public void setModifier(Modifiers modifier) {
        this.modifier = modifier;
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "modifier"), PersistentDataType.STRING, modifier.name());
    }

    public void setItemStats(ItemStatType stats) {
        itemStats = stats;
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "value"), PersistentDataType.INTEGER, itemStats.getValue());
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "stat"), PersistentDataType.STRING, itemStats.name());
    }

    public String getId() {
        return id;
    }
}
