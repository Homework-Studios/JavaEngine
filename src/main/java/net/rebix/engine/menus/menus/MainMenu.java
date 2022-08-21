package net.rebix.engine.menus.menus;

import net.rebix.engine.item.ItemStackBuilder;
import net.rebix.engine.menus.Menu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainMenu extends Menu {



    public MainMenu() {
        super("JavaEngine", 6);


    }

    @Override
    public boolean onUpdate() {

        ItemStack playerskull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerskullmeta = (SkullMeta) playerskull.getItemMeta();
        playerskullmeta.setOwner(player.getName());
        playerskull.setItemMeta(playerskullmeta);
        playerskull = new ItemStackBuilder(playerskull).setName(player.getDisplayName()).setPickupabel(false).build();
        items.set(0,playerskull);
        return true;
    }
}
