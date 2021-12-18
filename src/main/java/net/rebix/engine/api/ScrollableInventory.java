package net.rebix.engine.api;


import net.rebix.engine.Main;
import net.rebix.engine.util.enums.ButtonAction;
import net.rebix.engine.util.enums.InventoryButtonType;
import net.rebix.engine.events.customevents.ButtonClickEvent;
import net.rebix.engine.util.ItemBuilder;
import net.rebix.engine.items.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;

public class ScrollableInventory implements Listener {

    public static HashMap<Player,ScrollableInventory> PLAYER_INVENTORY = new HashMap<>();
    private Integer size;
    private Integer page = 1;
    private Integer pages = Main.INTEGER_LIMIT;
    private String name;
    private HashMap<Integer,ItemStack> contents = new HashMap<>();
    private Inventory inventory;
    private Player player;

    public ScrollableInventory(){
    }

    public ScrollableInventory create(@NotNull Player player, @NotNull String name, @NotNull Integer size) {
        //set values to the private variables
        this.size = size;
        this.name = name;
        this.player = player;
        PLAYER_INVENTORY.put(player,this);
        return this;
    }

    public ScrollableInventory create(@NotNull Player player, @NotNull String name, @NotNull Integer size, @Nullable Integer page) {
        //set values to the private variables
        this.size = size;
        this.name = name;
        this.player = player;
        if(page != null) this.page = page;
        PLAYER_INVENTORY.put(player,this);
        return this;
    }

    public ScrollableInventory create(@NotNull Player player, @NotNull String name, @NotNull Integer size, @Nullable Integer page, @Nullable Integer pages) {
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


        int slot;
        for(slot = 8; slot < size; ++slot) {
            Integer invenotryslot = slot-8 + (size-9) * (page-1);

            ItemStack fillitem = contents.get(invenotryslot);

            if(fillitem != null) inventory.setItem(slot,fillitem);
        }
        fillInButtonsAndPlaceholder();
        player.openInventory(inventory);
    }


    public void fillInButtonsAndPlaceholder() {
        inventory.setItem(0, new ItemBuilder(Material.PLAYER_HEAD).skull(InventoryButtonType.BLACK_ARROW_LEFT.getValue()).setButtonAction(ButtonAction.SCROLL_LEFT).build());
        inventory.setItem(8, new ItemBuilder(Material.PLAYER_HEAD).skull(InventoryButtonType.BLACK_ARROW_RIGHT.getValue()).setButtonAction(ButtonAction.SCROLL_RIGHT).build());
        for (int index = 1; index< 8; ++ index) inventory.setItem(index, ItemFactory.PLACEHOLDER);
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

    public void setContents(HashMap<Integer,ItemStack> itemStackList){
        this.contents = itemStackList;
        reloadInventory();
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
