package net.rebix.engine.util;

import java.io.IOException;

import net.rebix.engine.util.enums.LanguageType;
import org.bukkit.entity.Player;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Translator {

    List<String> TranslateList = new ArrayList<>();

    public void enable() {
        StringBuilder sb = new StringBuilder();
        try {
            for(Scanner scanner = new Scanner(new URL("https://raw.githubusercontent.com/Homework-Studios/github-storage/main/JavaEngine/Translations").openStream()); scanner.hasNext(); )
                TranslateList.add(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TranslateList.removeIf(line -> line.startsWith("#"));
    }

    public String Translate(String input, LanguageType languageType) {
        String out = input;
        String Language = languageType.getValue();


        return out;
    }


}
