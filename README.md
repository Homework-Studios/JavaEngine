# JavaEngine
My Plugin Engine


Things i have to add later
  - [ ] CabelAPI
  - [ ] ScorebordAPI
  - [ ] TablistAPI
  - [ ] TranslatorAPI

# How to use things

API's

Scrollabel Inventory API

```java
import net.rebix.engine.util.ItemBuilder;
import org.bukkit.Material;

import java.util.HashMap;

public class exampel {
    
    public void exampel() {
        ScrollableInventory scrollable_inventory = new ScrollableInventory().create(player, name, size, page, pages);
        HashMap<Integer, String> contents = new HashMap<>();
        contents.put(1, new ItemBuilder(Material.STONE).build());
        scrollable_inventory.setContents(contents);
        
        scrollable_inventory.reloadInventory();
    }
}
```

ItemBuilder API

```java
import net.rebix.engine.api.inventorycomponents.ButtonAction;
import net.rebix.engine.util.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class exampel {

    public void exampel() {
        ItemStack itemStack = new ItemBuilder(Material)
                .setUnbreakable(true).build();
                //you can add a lot here below is a list of functions
                //remember do .build(); at the end to return a itemstack
    }

}
```
functions(last updated 16.12.2021, 18:36 MEZ)
- ItemBuilder(Material material) can't be null but air
- setName(String name)
- setAmount(int amount)
- setLore(String... lore)
- addItemFlag(ItemFlag flag)
- setUnbreakabel(boolean unbreakabel)
- setLocalName(String name)
- setGlowing(boolean glowing)
- skull(String 64baseencodedskinvalue)
- setPickupabel(boolean pickupabel)
- setButtonAction(ButtonAction action) there is a listener (ButtonClickEvent look below)
- getItemBuilder(ItemStack item) returns the ItemBuilder class to modify the item
- build() returns the ItemStack


CustomEvents
Have to be registert like normal events
ButtonClickEvent

```java
import net.rebix.engine.api.inventorycomponents.ButtonAction;
import org.bukkit.event.Listener;

public class exampel implements Listener {

    @EventHandler
    public void ButtonClickEvent(ButtonClickEvent event) {
        Player player = event.getPlayer();
        ButtonAction action = event.getButtonAction();
    }
}
```

