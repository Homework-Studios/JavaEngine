package net.rebix.engine.util;

import net.rebix.engine.util.api.PlayerHead;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class items {
    //Utility Items
        public static ItemStack PLACEHOLDER = new itemBuilder(Material.GRAY_STAINED_GLASS_PANE).setPickupabel(false).setName(" ").build();

    //Buttons
        //Arrow Buttons
            public static ItemStack BLACK_BUTTON_LEFT = new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZWU5YTc1YmYwZGY3ODk3MTgzMDE1Y2NhMGIyYTdkNzU1YzYzMzg4ZmYwMTc1MmQ1ZjQ0MTlmYzY0NSJ9fX0=").getItem();
            public static ItemStack BLACK_BUTTON_RIGHT = new PlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgyYWQxYjljYjRkZDIxMjU5YzBkNzVhYTMxNWZmMzg5YzNjZWY3NTJiZTM5NDkzMzgxNjRiYWM4NGE5NmUifX19").getItem();
}
