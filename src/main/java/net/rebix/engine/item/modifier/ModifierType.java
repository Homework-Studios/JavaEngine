package net.rebix.engine.item.modifier;

import org.bukkit.ChatColor;
import org.bukkit.Color;



public enum ModifierType {
    OFFENCIVE(ChatColor.RED,"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIwZWYwNmRkNjA0OTk3NjZhYzhjZTE1ZDJiZWE0MWQyODEzZmU1NTcxODg2NGI1MmRjNDFjYmFhZTFlYTkxMyJ9fX0"),
    DEFENCIVE(ChatColor.GREEN,"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQwNjNiYTViMTZiNzAzMGEyMGNlNmYwZWE5NmRjZDI0YjA2NDgzNmY1NzA0NTZjZGJmYzllODYxYTc1ODVhNSJ9fX0"),
    UTILITY(ChatColor.WHITE,"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThjZGU2NzAwMzcyMmRiODE4Mjk5YmNiZWM4ODMyODlkMThkYzkzMzJkZTgwOTgzNDAwYTg3ZjIwNTQ0MzkyYSJ9fX0"),
    MAGIC(ChatColor.BLUE,"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRlNzE2NzFkYjVmNjlkMmM0NmEwZDcyNzY2YjI0OWMxMjM2ZDcyNjc4MmMwMGEwZTIyNjY4ZGY1NzcyZDRiOSJ9fX0"),;

    private final ChatColor color;
    private final String skull;
    ModifierType(ChatColor color, String skull) {
        this.color = color;
        this.skull = skull;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getSkull() {
        return skull;
    }
}
