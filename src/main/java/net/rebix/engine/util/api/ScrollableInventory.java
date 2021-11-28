package net.rebix.engine.util.api;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import net.rebix.engine.Main;
import net.rebix.engine.util.api.inventorycomponents.ButtonAction;
import net.rebix.engine.util.api.inventorycomponents.InventoryButton;
import net.rebix.engine.util.api.inventorycomponents.InventoryButtonType;
import net.rebix.engine.util.events.customevents.ButtonClickEvent;
import net.rebix.engine.util.itemBuilder;
import net.rebix.engine.util.items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class ScrollableInventory implements Listener {

    public static HashMap<Player,ScrollableInventory> PLAYER_INVENTORY = new HashMap<>();
    private Integer size;
    private Integer page = 1;
    private Integer pages = Main.INTEGER_LIMIT;
    private String name;
    private Inventory inventory;
    private Player player;

    public ScrollableInventory(){
    }

    public ScrollableInventory create(@NotNull Player player, @NotNull String name, @NotNull Integer size, @Nullable Integer page,@Nullable Integer pages) {
        //set values to the private variables
        this.size = size;
        this.name = name;
        this.player = player;
        if(page != null) this.page = page;
        if(page != null) this.pages = pages;
        PLAYER_INVENTORY.put(player,this);
        return this;
    }

    //utils
    public void reloadInventory(){
        inventory = Bukkit.createInventory(null,size,name + " - " + page);
        fillInButtonsAndPlaceholder();
        player.openInventory(inventory);
    }

    public void fillInButtonsAndPlaceholder() {
        inventory.setItem(0, new itemBuilder(Material.PLAYER_HEAD).skull(new InventoryButton(InventoryButtonType.BLACK_ARROW_LEFT).getSkullValue()).setButtonAction(ButtonAction.SCROLL_LEFT).build());
        inventory.setItem(8, new itemBuilder(Material.PLAYER_HEAD).skull(new InventoryButton(InventoryButtonType.BLACK_ARROW_RIGHT).getSkullValue()).setButtonAction(ButtonAction.SCROLL_RIGHT).build());
        for (Integer index = 1; index< 8; ++ index) inventory.setItem(index,items.PLACEHOLDER);

    }
    @EventHandler
    public void ButtonClickEvent(ButtonClickEvent event){
        switch (event.getButtonAction()){
            case SCROLL_LEFT:
                PLAYER_INVENTORY.get(event.getPlayer()).scroll(-1);
                break;
            case SCROLL_RIGHT:
                PLAYER_INVENTORY.get(event.getPlayer()).scroll(1);
                break;
            case SCROLL_LEFT_FAST:
                PLAYER_INVENTORY.get(event.getPlayer()).scroll(-10);
                break;
            case SCROLL_RIGHT_FAST:
                PLAYER_INVENTORY.get(event.getPlayer()).scroll(10);
                break;
            case SCROLL_LEFT_VERY_FAST:
                PLAYER_INVENTORY.get(event.getPlayer()).scroll(-100);
                break;
            case SCROLL_RIGHT_VERY_FAST:
                PLAYER_INVENTORY.get(event.getPlayer()).scroll(100);
                break;
        }
    }

    //set Values
    public void scroll(Integer direction) {
        page = direction + page;
        if(page < 1) page = 1;
        if(page > pages) page = pages;
        reloadInventory();
    }

    public void scrollTo(Integer page){
        this.page = page;
    }

    public void setContents(List<ItemStack> itemStackList){

    }


    //get Values
    public Integer getSize(){
        return size;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPages() {
        return pages;
    }
}
