package net.rebix.engine.combat.stats;

import java.util.HashMap;

public class Stats {
    HashMap<String, Double> allStats = new HashMap<>();

    public Stats() {
        StatType[] statTypes = StatType.values();
        for (int i = 0; i < statTypes.length; i++) {
            allStats.put(statTypes[i].name(), .0);
        }
    }

    public Stats(Stats stats) {
        StatType[] statTypes = StatType.values();
        for (int i = 0; i < statTypes.length; i++) {
            allStats.put(statTypes[i].name(), stats.getStat(statTypes[i]));
        }
    }

    public Stats getbyString(String stats) {
        String[] statsArray = stats.split(",");
        StatType[] statTypes = StatType.values();
        //System.out.println(String.join(",", statsArray));
        allStats = new Stats().allStats;

        for (int i = 0; i < statTypes.length; i++) {
            allStats.put(statTypes[i].name(), .0);
        }
        if (!stats.equals("")) {
            if (statsArray.length == 0) return this;
            for (int i = 0; i < statsArray.length; i++) {
                String[] stat = statsArray[i].split("=");
                allStats.put(stat[0], Double.valueOf(stat[1]));
            }
        }

        return this;
    }

    public String getString() {
        String stats = "";
        StatType[] statTypes = StatType.values();
        for (int i = 0; i < statTypes.length; i++) {
            stats += statTypes[i].name() + "=" + statTypes[i].getValue() + ",";
        }
        return stats;
    }

    public Double getStat(StatType stat) {
        return allStats.get(stat.name());
    }

    public Double getStat(String stat) {
        return allStats.get(stat);
    }

    public void add(Stats stat) {
        if (stat != null) {
            StatType[] statTypes = StatType.values();
            for (int i = 0; i < statTypes.length; i++) {
                StatType statType = statTypes[i];
                Double value = stat.getStat(statType) + allStats.get(statType.name());
                allStats.put(statType.name(), value);
            }
        }
    }

    public void add(StatType stat, Double value) {
        setStat(stat, getStat(stat) + value);
    }

    public void setStat(StatType stat, double value) {
        allStats.put(stat.name(), value);
    }

    public void setStat(String stat, double value) {
        allStats.put(stat, value);
    }

    public void multiply(Stats statMultiply, boolean zeroSafe) {
        if (statMultiply != null) {
            StatType[] statTypes = StatType.values();
            for (int i = 0; i < statTypes.length; i++) {
                StatType statType = statTypes[i];
                Double value = statMultiply.getStat(statType) * allStats.get(statType.name());
                if (value != 0 || !zeroSafe)
                    allStats.put(statType.name(), value);
            }
        }
    }
}
