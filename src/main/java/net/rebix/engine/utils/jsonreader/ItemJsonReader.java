package net.rebix.engine.utils.jsonreader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.rebix.engine.item.ItemAbility.ItemAbility;
import net.rebix.engine.item.Rarity;
import net.rebix.engine.item.enchant.Enchantment;
import net.rebix.engine.item.modifier.Modifier;
import net.rebix.engine.item.stats.ItemStats;
import org.bukkit.Material;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemJsonReader {
    JsonObject json;
    File file;

    public ItemJsonReader(File file) {
        this.file = file;
        try {
            this.json = JsonParser.parseReader(new FileReader(file)).getAsJsonObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() {
        return file.getName().replace(".json", "");
    }

    public String getName() {
        return json.get("name").getAsString();
    }
    public void setName(String name) {
        json.addProperty("name", name);
    }

    public String getLore() {
        return json.get("lore").getAsString();
    }
    public void setLore(List<String> lore) {
        json.addProperty("lore", String.join("&&", lore));
    }

    public Rarity getRarity() {
        return Rarity.valueOf(json.get("rarity").getAsString());
    }
    public void setRarity(Rarity rarity) {
        json.addProperty("rarity", rarity.name());
    }

    public List<String> upgradeitems() {
        return Arrays.asList(json.get("upgradeitems").getAsString().split(","));
    }
    public void setUpgradeitems(List<String> upgradeitems) {
        json.addProperty("upgradeitems", String.join(",", upgradeitems));
    }

    public Integer getLevel() {
        return json.get("level").getAsInt();
    }
    public void setLevel(Integer level) {
        json.addProperty("level", level);
    }

    public List<Modifier> getModifiers() {
        List<Modifier> modifiers = new ArrayList<>();
        for(String modifier : json.get("modifiers").getAsString().split(",")) {
            if(modifier != null)
                modifiers.add(Modifier.getbyString(modifier));
        }
        return modifiers;
    }
    public void setModifiers(List<Modifier> modifiers) {
        String out = "";
        for (Modifier modifier : modifiers) {
            out += modifier.getString() + ",";
        }
        json.addProperty("modifiers", out);
    }


    public List<Enchantment> getEnchantments() {
        List<Enchantment> enchantments = new ArrayList<>();
        for(String enchantment : json.get("enchantments").getAsString().split(",")) {
            enchantments.add(Enchantment.getbyString(enchantment));
        }
        return enchantments;
    }
    public void setEnchantments(List<Enchantment> enchantments) {
        String out = "";
        for (Enchantment enchantment : enchantments) {
            out += enchantment.getString() + ",";
        }
        json.addProperty("enchantments", out);
    }

    public ItemStats getStats() {
        return ItemStats.getbyString(json.get("stats").getAsString());
    }
    public void setStats(ItemStats stats) {
        json.addProperty("stats", stats.getString());
    }

    public List<ItemAbility> getAbilities() {
        List<ItemAbility> abilities = new ArrayList<>();
        //if(json.get("abilities").getAsString().contains(","))
            for(String ability : json.get("abilities").getAsString().split(",")) {
                abilities.add(ItemAbility.abilities.get(ability));
            }
        return abilities;
    }
    public void setAbilities(List<ItemAbility> abilities) {
        String out = "";
        for (ItemAbility ability : abilities) {
            out += ability.getId() + ",";
        }
        json.addProperty("abilities", out);
    }
    

    public List<Material> getLvlSkins() {
        List<Material> skins = new ArrayList<>();
        for(String skin : json.get("lvlskins").getAsString().split(",")) {

            skins.add(Material.valueOf(skin));
        }
        return skins;
    }

    public void save() {
        try {
            new FileWriter(file).write(json.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getLocal() {
        return json.get("itemtag").getAsString();
    }
}
