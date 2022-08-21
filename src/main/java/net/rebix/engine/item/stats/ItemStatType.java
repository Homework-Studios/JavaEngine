package net.rebix.engine.item.stats;

public enum ItemStatType {
    DAMAGE(0),
    HEALTH(0),
    SPEED(0),
    MANA(0),
    ;

    private Integer value;
    ItemStatType(Integer value) {
        this.value = value;

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
