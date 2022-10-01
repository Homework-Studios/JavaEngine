package net.rebix.engine.api.inventory;


import net.rebix.engine.JavaEngine;
import net.rebix.engine.item.ItemStackBuilder;
import net.rebix.engine.utils.customevents.ButtonClickEvent;
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
import java.util.Objects;

public class ScrollableInventory implements Listener {

    public static HashMap<Player, ScrollableInventory> PLAYER_INVENTORY = new HashMap<>();
    private Integer size;
    private Integer page = 1;
    private Integer pages = JavaEngine.INTEGER_LIMIT;
    private String name;
    private HashMap<Integer, ItemStack> contents = new HashMap<>();
    private Inventory inventory;
    private Player player;

    public ScrollableInventory() {
    }

    public ScrollableInventory create(@NotNull Player player, @NotNull String name, @NotNull Integer size) {
        //set values to the private variables
        this.size = size;
        this.name = name;
        this.player = player;
        PLAYER_INVENTORY.put(player, this);
        return this;
    }

    public ScrollableInventory create(@NotNull Player player, @NotNull String name, @NotNull Integer size, @Nullable Integer page) {
        //set values to the private variables
        this.size = size;
        this.name = name;
        this.player = player;
        if (page != null) this.page = page;
        PLAYER_INVENTORY.put(player, this);


        return this;
    }

    public ScrollableInventory create(@NotNull Player player, @NotNull String name, @NotNull Integer size, @Nullable Integer page, @Nullable Integer pages) {
        //set values to the private variables
        this.size = size;
        this.name = name;
        this.player = player;
        if (page != null) this.page = page;
        if (page != null) this.pages = pages;
        PLAYER_INVENTORY.put(player, this);
        return this;
    }

    //utils
    public void reloadInventory() {
        inventory = Bukkit.createInventory(null, size, name + " - " + page);


        int slot;
        for (slot = 8; slot < size; ++slot) {
            Integer invenotryslot = slot - 8 + (size - 9) * (page - 1);

            ItemStack fillitem = contents.get(invenotryslot);

            if (fillitem != null) inventory.setItem(slot, fillitem);
        }
        fillInButtonsAndPlaceholder();
        player.openInventory(inventory);
    }


    public void fillInButtonsAndPlaceholder() {
        for (int index = 0; index < 9; ++index)
            inventory.setItem(index, new ItemStackBuilder(Material.BLACK_STAINED_GLASS_PANE).setPickupabel(false).build());
        if (page != 1)
            inventory.setItem(0, new ItemStack(Material.ARROW));
        if (!Objects.equals(page, pages))
            inventory.setItem(8, new ItemStack(Material.ARROW));

    }

    @EventHandler
    void ButtonClickEvent(ButtonClickEvent event) {
        int scroll = 0;
        switch (event.getButtonActionE()) {
            case PICKUP_ALL:
                scroll = 1;
                break;
            case PICKUP_HALF:
                scroll = 10;
                break;
            case MOVE_TO_OTHER_INVENTORY:
                scroll = 100;
                break;
        }
        switch (event.getButtonAction()) {
            case "BUTTON.ACTION.LEFT":
                PLAYER_INVENTORY.get(event.getPlayer()).scroll(-scroll);
                break;
            case "BUTTON.ACTION.RIGHT":
                PLAYER_INVENTORY.get(event.getPlayer()).scroll(scroll);
                break;
        }
    }

    //set Values
    public void scroll(Integer direction) {
        page = direction + page;
        if (page < 1) page = 1;
        if (page > pages) page = pages;
        reloadInventory();
    }

    public void scrollTo(Integer page) {
        this.page = page;
    }

    public void setContents(HashMap<Integer, ItemStack> itemStackList) {
        this.contents = itemStackList;
        reloadInventory();
    }


    //get Values
    public Integer getSize() {
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
