package net.rebix.engine.item.stats;

import net.rebix.engine.item.modifier.ModifierType;

public enum ItemStatType {
    DAMAGE(0, "✲", ModifierType.OFFENCIVE),
    CRITIDAMAGE(0, "☣", ModifierType.OFFENCIVE),
    CRITCHANCHE(0, "☣%", ModifierType.OFFENCIVE),
    RANGEDAMAGE(0, "➹✲", ModifierType.OFFENCIVE),
    STRENGTH(0, "✘", ModifierType.OFFENCIVE),
    SUPERCRITCHANCE(0, "☣%ⓢ", ModifierType.OFFENCIVE),
    SUPERCRITDAMAGE(0, "☣ⓢ", ModifierType.OFFENCIVE),

    HEALTH(0, "♡", ModifierType.DEFENCIVE),
    HEALTHREGEN(0, "♡", ModifierType.DEFENCIVE),
    ARMOR(0, "❈",ModifierType.DEFENCIVE),

    DEXTERITY(0, "ஐ", ModifierType.UTILITY),
    SPEED(0, "Ⓑ/ⓢ",ModifierType.UTILITY),
    AGILITY(0, "㊚",ModifierType.UTILITY),

    MANA(0, "☯",ModifierType.MAGIC),
    MAGICDAMAGE(0, "☯✲",ModifierType.MAGIC),
    MANAREGEN(0, "☯/ⓢ", ModifierType.MAGIC),

    ;

    private Integer value;
    private final String symbol;
    private final ModifierType type;
    ItemStatType(Integer value, String symbol, ModifierType type) {
        this.type = type;
        this.symbol = symbol;
        this.value = value;

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public ModifierType getType() {
        return type;
    }
}
