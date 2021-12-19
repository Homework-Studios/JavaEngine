# JavaEngine
My Plugin Engine


Things I have to add later
- [ ] CabelAPI
- [ ] ScorebordAPI
- [ ] TablistAPI
- [x] TranslatorAPI
- [ ] AutoUpdater

## API

#### Scrollabel Inventory API

```java
import net.rebix.engine.util.ItemBuilder;
import org.bukkit.Material;

import java.util.HashMap;

public class example {

    public example() {
        ScrollableInventory scrollable_inventory = new ScrollableInventory().create(player, name, size, page, pages);
        HashMap<Integer, String> contents = new HashMap<>();
        contents.put(1, new ItemBuilder(Material.STONE).build());
        scrollable_inventory.setContents(contents);

        scrollable_inventory.reloadInventory();
    }
}
```

page an pages can be left out page will be 1 and pages will be integer limit

#### ItemBuilder API

```java

import net.rebix.engine.util.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class example {

    public example() {
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

#### EntityHider

```java
import net.rebix.engine.api.packets.EntityHider;

public class example {

    public void HideEntityForAllPlayers() {
        new EntityHider(entity).HideEntity();
    }

    public void HideEntityForSpecificPlayer() {
        new EntityHider(entity).HideEntityForPlayer(player);
    }
}
```


#### Translator

```java
import net.rebix.engine.api.Translator;
import net.rebix.engine.util.enums.LanguageType;

public class example {

    public void translatedprint() {
        new Translator().Translate(translateidentifier, LanguageType.language);

        new Translator().Translate("engine.load", LanguageType.German);
        //this would equal "JavaEngine wurde geladen"
    }
}
```
you can find the list of translations here
[Translation List](https://github.com/Homework-Studios/github-storage/blob/main/JavaEngine/Translations "Translation List")




##  **CustomEvents**
> Have to be registert like normal events

#### ButtonClickEvent

```java
import net.rebix.engine.util.enums.ButtonAction;
import org.bukkit.event.Listener;

public class exampel implements Listener {

    @EventHandler
    public void ButtonClickEvent(ButtonClickEvent event) {
        Player player = event.getPlayer();
        ButtonAction action = event.getButtonAction();
    }
}
```


## Config
can be found under plugin/Java Engine/config.yaml

you can get the default config with the writedefaultcfg command ony in the console
```yaml
#This is the cfg for The JavaEngine
Version: 1.0
#The Language supported are: English,German
Language: English

#Auto update
AutoUpdate: true


```
