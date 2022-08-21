package net.rebix.engine.item.ItemAbility;

import net.rebix.engine.item.EItem;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class ItemAbility {
    ItemAbilityType trigger;
    String name;
    List<String> description;
    String id;
    Float ticks_unit_next_use;
    public static HashMap<String, ItemAbility> abilities = new HashMap<>();

    public ItemAbility(ItemAbilityType trigger, String name, List<String> description, String ability_id) {
        this.trigger = trigger;
        this.name = name;
        this.description = description;
        this.id = ability_id;
        register();
    }

    public ItemAbility(ItemAbilityType trigger, String name, List<String> description, String ability_id, Float ticks_unit_next_use) {
        this.ticks_unit_next_use = ticks_unit_next_use;
        this.trigger = trigger;
        this.name = name;
        this.description = description;
        this.id = ability_id;
        register();
    }

    public String getId() {
        return id;
    }

    public ItemAbilityType getTrigger() {
        return trigger;
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    public Float getTicks_unit_next_use() {
        return ticks_unit_next_use;
    }

    public boolean use(Player player, EItem item) {
        return false;
    }

    void register() {
        abilities.put(id, this);
    }
}
