package net.rebix.engine.api;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandPermissionManager {

    public static List<String> PermissionList = new ArrayList<>();

    public void enable() {
        StringBuilder sb = new StringBuilder();
        try {
            for(Scanner scanner = new Scanner(new URL("https://raw.githubusercontent.com/Homework-Studios/github-storage/main/JavaEngine/CommandPermissionLevels").openStream()); scanner.hasNext(); )
                PermissionList.add(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        PermissionList.removeIf(line -> line.startsWith("#"));
    }

    public String getPermissionLevelOf(String action) {
        String level = "error";
        for (String l:PermissionList)
            if(l.startsWith(action))
                level = l.split("::")[1];
        return "permission.level." + level;
    }

    public boolean checkPermissionLevelOfPlayer(Player player, String action) {
        String errorMessage = new Translator().Translate("engine.permission.level.error");
        errorMessage = errorMessage.replaceAll("%%LEVEL%%",getPermissionLevelOf(action).replaceAll("permission.level.",""));
        if(!player.isOp()) player.sendMessage(errorMessage);
        return player.isOp();
    }
}
