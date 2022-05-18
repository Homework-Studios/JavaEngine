package net.rebix.engine.item;

import net.rebix.engine.Main;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EngineItem {
    private String name;
    private List<String> description;
    private Material material;
    private ItemStack item;
    private final String id;
    private List<ItemAbility> abilities = new ArrayList<>();

    public EngineItem(String id, String name, Material material, String... description) {
        this.name = name;
        this.id = id;
        this.description = Arrays.asList(description);
        this.material = material;
        this.item = new ItemBuilder(material,id).setName(name).setLore(description).build();
        updateLore();
    }

    public EngineItem(ItemBuilder itemBuilder) {
        this.id = itemBuilder.getID();
        this.name = itemBuilder.getName();
        this.description = itemBuilder.getLore();
        this.material = itemBuilder.getMaterial();
        this.item = itemBuilder.build();
        updateLore();
    }

    public EngineItem(String id, String name, String headkey, String... description) {
        this.name = name;
        this.id = id;
        this.description = Arrays.asList(description);
        this.material = Material.PLAYER_HEAD;
        this.item = new ItemBuilder(material,id).skull(headkey).setName(name).setLore(description).build();
        updateLore();
    }

    public EngineItem(ItemStack item) {
        this.item = item;
        this.id = new ItemBuilder(item).getID();
        this.name = item.getType().name();
        this.description = Objects.requireNonNull(item.getItemMeta()).getLore();
        this.material = item.getType();
        updateLore();
    }
    public EngineItem(Material material) {
        this.material = material;
        this.item = new ItemBuilder(material).build();
        this.name = material.name();
        this.description = null;
        this.id = material.name();
        updateLore();
    }

    public EngineItem addAbility(ItemAbility ability) {
        abilities.add(ability);
        Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().set(new NamespacedKey(Main.plugin, ability.getId()), PersistentDataType.STRING, "true");
        updateLore();
        return this;
    }

    public EngineItem removeAbility(ItemAbility ability) {
        abilities.remove(ability);
        Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer().remove(new NamespacedKey(Main.plugin, ability.getId()));
        updateLore();
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public ItemStack getItem() {
        return item.clone();
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public List<ItemAbility> getAbilities() {
        return abilities;
    }

    public void updateLore() {
        if(description != null && item.getItemMeta() != null) {
            List<String> lore = new ArrayList<>(description);
            for(ItemAbility ability : abilities) {
                lore.add("§2§lUSE: §6§l"+ability.getTrigger().name().replaceAll("_"," ")+"§2§l To Use: "+"§a§l"+ ability.getName() + "§f§o\n" + ability.getDescription());
                if(ability.getTicks_unit_next_use() > 1) {
                    float cdcounter = ability.getTicks_unit_next_use()/20f;
                    cdcounter = Math.round(cdcounter*10f)/10f;
                    lore.add("§2Cooldown: §3"+cdcounter+" §2Seconds");
                }
            }
            ItemMeta meta = item.getItemMeta();
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    public void init() {

    }

    EngineItem setPickupable(boolean pickupable) {
        item = new ItemBuilder(item).setPickupabel(pickupable).build();
        return this;
    }

    EngineItem setButtonAction(String action){
        ItemMeta itemMeta = Objects.requireNonNull(item.getItemMeta());
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.plugin,"ButtonAction"), PersistentDataType.STRING, String.valueOf(action));
        item.setItemMeta(itemMeta);
        return this;
    }

    public void register() {
        init();
        Main.getItemFactory().registerItem(item, this);
    }
}
