package net.rebix.engine.combat.stats;

import net.rebix.engine.item.modifier.ModifierType;

public enum StatType {
    DAMAGE(.0, "\uD83D\uDDE1", ModifierType.OFFENCIVE),
    CRITICALDAMAGE(.0, "☣", ModifierType.OFFENCIVE),
    CRITICALCHANCHE(.0, "☣%", ModifierType.OFFENCIVE),
    RANGEDAMAGE(.0, "➹", ModifierType.OFFENCIVE),
    STRENGTH(.0, "✘", ModifierType.OFFENCIVE),
    SUPERCRITCHANCE(.0, "☣☣%", ModifierType.OFFENCIVE),
    SUPERCRITDAMAGE(.0, "☣☣", ModifierType.OFFENCIVE),

    HEALTH(.0, "♡", ModifierType.DEFENCIVE),
    HEALTHREGEN(.0, "♡/ⓢ", ModifierType.DEFENCIVE),
    ARMOR(.0, "❈", ModifierType.DEFENCIVE),

    DEXTERITY(.0, "ஐ", ModifierType.UTILITY),
    SPEED(.0, "ⓢ", ModifierType.UTILITY),
    AGILITY(.0, "㊚", ModifierType.UTILITY),
    ENGINEERING(.0, "⚙", ModifierType.UTILITY),

    MANA(.0, "☯", ModifierType.MAGIC),
    MAGICDAMAGE(.0, "\uD83D\uDDE1", ModifierType.MAGIC),
    MANAREGEN(.0, "☯/ⓢ", ModifierType.MAGIC),

    ;

    private final String symbol;
    private final ModifierType type;
    private Double value;

    StatType(Double value, String symbol, ModifierType type) {
        this.type = type;
        this.symbol = symbol;
        this.value = value;

    }

    public Double getValue() {
        return value;
    }

    public StatType setValue(Double value) {
        this.value = value;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public ModifierType getType() {
        return type;
    }

    public String toString() {
        return type.getColor() + symbol + ": " + value;
    }
}
