package net.rebix.engine.item;

import org.bukkit.ChatColor;

public enum Rarity {

    NORMAL(ChatColor.WHITE),
    UNCOMMON(ChatColor.GRAY),
    RARE(ChatColor.GREEN),
    VERY_RARE(ChatColor.BLUE),
    EPIC(ChatColor.LIGHT_PURPLE),
    VERY_EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    ARTIFACT(ChatColor.RED),

    GODLY(ChatColor.YELLOW);

    private final ChatColor color;

    Rarity(ChatColor color) {
        this.color = color;
    }

    public static Rarity get(String rarity) {
        for (Rarity r : Rarity.values()) {
            if (r.name().equalsIgnoreCase(rarity)) return r;
        }
        return NORMAL;
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
            case GODLY:
                return GODLY;
            default:
                return NORMAL;
        }
    }

    public ChatColor getColor() {
        return color;
    }
}
