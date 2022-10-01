package net.rebix.engine.item.modifier;

public enum ModifierAction {
    Multiplier("%"),
    Adder(""),
    GeneralMultiplier("%"),
    CustomEffect("");

    final String symbol;

    ModifierAction(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
