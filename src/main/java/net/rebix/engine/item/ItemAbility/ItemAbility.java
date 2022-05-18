package net.rebix.engine.item.ItemAbility;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemFactory;
import net.rebix.engine.util.enums.ItemAbilityType;
import org.bukkit.entity.Player;

public class ItemAbility {
    ItemAbilityType trigger;
    String name;
    String description;
    String id;
    Float ticks_unit_next_use;

    public ItemAbility(ItemAbilityType trigger, String name, String description, String ability_id) {
        this.trigger = trigger;
        this.name = name;
        this.description = description;
        this.id = ability_id;
        register();
    }

    public ItemAbility(ItemAbilityType trigger, String name, String description, String ability_id, Float ticks_unit_next_use) {
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

    public String getDescription() {
        return description;
    }

    public Float getTicks_unit_next_use() {
        return ticks_unit_next_use;
    }

    public void use(Player player, EngineItem item) {

    }

    void register() {
        ItemFactory.registerAbility(id, this);
    }
}
