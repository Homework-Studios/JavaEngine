package net.rebix.engine.utils;

import com.google.gson.JsonObject;
import net.rebix.engine.item.EItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class JsonReader {
    JsonObject json;

    public JsonReader(JsonObject json) {
        this.json = json;
    }

    public static JsonObject convertItemHashmapToJsonObject(HashMap<Integer, ItemStack> item) {
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < item.size(); i++) {
            ItemStack itemStack = item.get(item.keySet().toArray()[i]);
            if (EItem.isEItem(itemStack)) {
                jsonObject.addProperty(i + "", "eitem " + new EItem(itemStack).getJson().toString().replaceAll("\"", ""));
            } else {
                jsonObject.addProperty(i + "", "itemstack " + itemStack.getType());
            }

        }
        return jsonObject;
    }

    public static HashMap<Integer, ItemStack> convertJsonObjectToItemHashmap(JsonObject jsonObject) {
        HashMap<Integer, ItemStack> item = new HashMap<>();
       

        return item;
    }

    public String getString(String key) {
        if (json.has(key)) {
            String value = json.get(key).getAsString();
            if (value != null) return value;
        }
        return "";
    }

    public Double getDouble(String key) {
        if (json.has(key)) return json.get(key).getAsDouble();
        return 0.0;
    }

    public Integer getInteger(String key) {
        if (json.has(key)) return json.get(key).getAsInt();
        return 0;
    }
}
