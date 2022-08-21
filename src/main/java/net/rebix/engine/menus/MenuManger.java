package net.rebix.engine.menus;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuManger {
    public static HashMap<Player, Menu> menus = new HashMap<>();

    public static void register(Menu menu, Player player) {
        menus.put(player, menu);
    }
}
