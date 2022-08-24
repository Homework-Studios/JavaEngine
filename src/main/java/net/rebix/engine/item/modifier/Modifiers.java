package net.rebix.engine.item.modifier;

public enum Modifiers {
    Multiplier("%"),
    Adder(""),
    GeneralMultiplier("%"),
    CustomEffect("");

    final String symbol;
    Modifiers(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }
}
