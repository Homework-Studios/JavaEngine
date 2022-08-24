package net.rebix.engine.item;

import org.bukkit.ChatColor;

import java.awt.*;

public enum Rarity {

    NORMAL(ChatColor.WHITE),
    UNCOMMON(ChatColor.GRAY),
    RARE(ChatColor.GREEN),
    VERY_RARE(ChatColor.BLUE),
    EPIC(ChatColor.LIGHT_PURPLE),
    VERY_EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.YELLOW),
    ARTIFACT(ChatColor.RED);

    private ChatColor color;
    Rarity(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }

    public static Rarity getNext(Rarity rarity) {
        switch (rarity) {
            case NORMAL:
                return UNCOMMON;
            case UNCOMMON:
                return RARE;
            case RARE:
                return VERY_RARE;
            case VERY_RARE:
                return EPIC;
            case EPIC:
                return VERY_EPIC;
            case VERY_EPIC:
                return LEGENDARY;
            case LEGENDARY:
            case ARTIFACT:
                return ARTIFACT;
            default:
                return NORMAL;
        }
    }
}
