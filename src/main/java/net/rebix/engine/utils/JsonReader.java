package net.rebix.engine.utils;

import com.google.gson.JsonObject;

public class JsonReader {
    JsonObject json;

    public JsonReader(JsonObject json) {
        this.json = json;
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
