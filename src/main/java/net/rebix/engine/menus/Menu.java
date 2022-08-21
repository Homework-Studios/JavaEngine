package net.rebix.engine.menus;

import net.rebix.engine.item.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    public String name;
    public final int size;
    public Inventory inventory;
    public Object parent;

    public List<ItemStack> items = new ArrayList<>();
    public Player player;

    public Menu(String name, int size, Menu parent){
        this.name = name;
        this.size = size;
        this.parent = parent;

        inventory = Bukkit.createInventory(null, size*9, name);
    }

    public Menu(String name, int size){
        this.name = name;
        this.size = size;

        inventory = Bukkit.createInventory(null, size*9, name);
    }

    public Menu(String name, int size, Inventory parent){
        this.name = name;
        this.size = size;
        this.parent = parent;

        inventory = Bukkit.createInventory(null, size*9, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        if(player != null) {
            update();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        MenuManger.register(this, player);
        update();
    }

    public void update() {
        fillEmpty();
        if(onUpdate()) {
            inventory.setContents(items.toArray(new ItemStack[items.size()]));
            if (player.getInventory() == inventory) {
                player.updateInventory();
            } else player.openInventory(inventory);
        }
    }

    public boolean onUpdate() {
        return true;
    }

    public void close() {
        player.closeInventory();
    }

    public void goBack() {
        if(parent instanceof Menu) {
            ((Menu) parent).setPlayer(player);
        } else if(parent instanceof Inventory) {
            for(Player p : MenuManger.menus.keySet())
                if(MenuManger.menus.get(p).inventory == parent) {
                    p.openInventory((Inventory) parent);
                    break;
                }
            player.openInventory((Inventory) parent);
        }
        close();
    }

    public void fillEmpty() {
        for(int i = 0; i < inventory.getSize(); i++) {
            items.add(new ItemStackBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").setPickupabel(false).build());
        }
    }
}
