package net.rebix.engine.item.modifier;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.rebix.engine.JavaEngine;
import net.rebix.engine.combat.stats.StatType;
import net.rebix.engine.item.EItem;
import net.rebix.engine.item.ItemStackBuilder;
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
    public static HashMap<String, Modifier> registered = new HashMap<>();
    private ModifierAction action;
    private String id;
    private ItemStack item = new ItemStack(Material.PLAYER_HEAD);
    private ItemMeta meta;
    private StatType itemStats;
    private double value;

    public Modifier(StatType type, ModifierAction action, Double value) {
        this.action = action;
        this.itemStats = type;
        itemStats.setValue(value);
    }

    public Modifier(ItemStack item) {
        if (item.hasItemMeta())
            if (Objects.requireNonNull(item.getItemMeta()).getLocalizedName().contains("modifier")) {
                meta = item.getItemMeta();
                action = ModifierAction.valueOf(meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "modifier"), PersistentDataType.STRING));
                id = meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING);
                itemStats = StatType.valueOf(meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "stat"), PersistentDataType.STRING));
                value = meta.getPersistentDataContainer().get(new NamespacedKey(JavaEngine.plugin, "value"), PersistentDataType.DOUBLE);
                item.setItemMeta(meta);
                this.item = item;
            }
    }

    public Modifier(File file) {
        try {
            meta = item.getItemMeta();
            JsonObject json = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
            id = file.getName().replace(".json", "");
            setItemStats(StatType.valueOf(json.get("stat").getAsString()));
            setAction(ModifierAction.valueOf(json.get("modifier").getAsString()));
            String[] split = json.get("value").getAsString().split(":");
            value = Double.parseDouble(split[0]);
            itemStats = StatType.valueOf(json.get("stat").getAsString());

            itemStats.setValue(value);
            setValue(itemStats.getValue());
            meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "id"), PersistentDataType.STRING, id);
            meta.setLocalizedName("modifier");
            meta.setDisplayName(itemStats.getType().getColor() + id);
            meta.setLore(Collections.singletonList(getLoreString()));
            SkullMeta skullMeta = (SkullMeta) meta;
            GameProfile gameProfile = new GameProfile(UUID.fromString("0ed8b527-d3cf-48a4-b9fc-c35c9efee447"), null);
            gameProfile.getProperties().put("textures", new Property("textures", getType().getSkull()));
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

    public static Modifier get(String id) {
        Modifier modifier = registered.get(id);
        if (modifier != null) {

            StatType itemStats = modifier.itemStats;
            itemStats.setValue(modifier.value);
            modifier.setItemStats(itemStats);
        }

        return modifier;
    }

    public static ItemStack getEmptyItem(int lvl, int slot) {
        if (lvl >= slot)
            return new ItemStackBuilder(Material.WHITE_STAINED_GLASS_PANE).setName("§fEmpty").setPickupabel(false).build();
        else
            return new ItemStackBuilder(Material.RED_STAINED_GLASS_PANE).setName("§4Locked").setPickupabel(false).build();
    }

    public static Modifier getbyString(String modifier) {
        if (modifier != null)
            return get(modifier);
        return null;
    }

    public static void registerAll() {
        String parent = JavaEngine.plugin.getDataFolder().getAbsolutePath() + "/Modifier";
        List<File> files = new ArrayList<>();
        EItem.listf(parent, files);

        for (File file : files) {
            new Modifier(file).register();
        }
        // updateForPlayers();
    }

    public static void updateForPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Inventory inv = player.getInventory();
            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack item = inv.getItem(i);
                if (item == null) continue;
                if (item.getType() == Material.AIR) continue;
                if (item.getItemMeta() == null) continue;
                if (item.getItemMeta().getLocalizedName().contains("modifier")) {
                    Modifier mod = new Modifier(Objects.requireNonNull(player.getInventory().getItem(i)));
                    mod.updateItem(inv, i);
                }
            }
        }
    }

    public ModifierType getType() {
        return itemStats.getType();
    }

    public ModifierAction getAction() {
        return action;
    }

    public void setAction(ModifierAction action) {
        this.action = action;
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "modifier"), PersistentDataType.STRING, action.name());
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

    public void updateItem(Inventory inv, int i) {
        updateValues();
        inv.setItem(i, getItem());
    }

    private void updateValues() {
        Modifier update = get(id);
        setAction(update.getAction());
        setItemStats(update.itemStats);
        meta.setDisplayName(itemStats.getType().getColor() + id);
        meta.setLore(Collections.singletonList(getLoreString()));
        item.setItemMeta(meta);
    }

    private String getLoreString() {
        return itemStats.getType().getColor() + itemStats.name() + " §f: " + itemStats.getType().getColor() + itemStats.getSymbol() + " " + itemStats.getValue() + action.getSymbol();
    }

    public void setItemStats(StatType stats) {
        itemStats = stats;
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "value"), PersistentDataType.DOUBLE, itemStats.getValue());
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "stat"), PersistentDataType.STRING, itemStats.name());
    }

    public String getId() {
        return id;
    }

    public StatType getStat() {
        return itemStats;
    }

    public Double getValue() {
        return value;
    }

    private void setValue(double valueOf) {
        meta.getPersistentDataContainer().set(new NamespacedKey(JavaEngine.plugin, "value"), PersistentDataType.DOUBLE, valueOf);
        value = valueOf;
    }
}
