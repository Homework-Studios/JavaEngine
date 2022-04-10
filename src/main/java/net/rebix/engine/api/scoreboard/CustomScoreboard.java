package net.rebix.engine.api.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomScoreboard {

    private String Name;
    private Scoreboard scoreboard;
    private List<Score> scores = new ArrayList<>();
    private DisplaySlot DisplaySlot;
    private Objective Objective;

    public CustomScoreboard(String name, DisplaySlot displaySlot) {
        Name = name;
        DisplaySlot = displaySlot;
        create();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void create() {
        scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        for (int index = 0; index <= 14; index ++) {
            Score score = null;
            scores.add(score);
        }
        if(scoreboard.getObjective(Name) == null) {
            Objective = scoreboard.registerNewObjective(Name, "");
        } else Objective = scoreboard.getObjective(Name);
        assert Objective != null;
        Objective.setDisplaySlot(DisplaySlot);
        for (String entry: scoreboard.getEntries()){
            scoreboard.resetScores(entry);
        }
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setLine(int line, String content) {
        scores.set(line, Objective.getScore(content));
        scores.get(line).setScore(line);

    }

    public org.bukkit.scoreboard.DisplaySlot getDisplaySlot() {
        return DisplaySlot;
    }

    public void setDisplaySlot(org.bukkit.scoreboard.DisplaySlot displaySlot) {
        DisplaySlot = displaySlot;
    }

    public void sendScoreboardToPlayer(Player player) {
        player.setScoreboard(scoreboard);
    }
}
