package net.rebix.engine.item;

import net.rebix.engine.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EngineItem {
    private String name;
    private List<String> description;
    private Material material;
    private ItemStack item;
    private final String id;

    public EngineItem(String id, String name, List<String> description, Material material) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.material = material;
        this.item = new ItemBuilder(material,id).setName(name).setLore(description).build();

        Main.getItemFactory().registerItem(item);
    }

    public EngineItem(ItemStack item) {
        this.item = item;
        this.id = new ItemBuilder(item).getID();
        this.name = item.getType().name();
        this.description = item.getItemMeta().getLore();
        this.material = item.getType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }
}
