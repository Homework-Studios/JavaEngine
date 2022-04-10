package net.rebix.engine.api;

import java.io.IOException;

import net.rebix.engine.Main;
import net.rebix.engine.util.enums.LanguageType;
import org.bukkit.entity.Player;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Translator {

    public static List<String> TranslateList = new ArrayList<>();

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
        String Language = languageType.getValue();
        for (String line : TranslateList) {
            if(line.contains("=")){
            String keyFound = line.split("=")[0];
            String content = line.split("=")[1];
            if(keyFound.equals(input)) {
                String[] translations = content.split(";;");
                for(String translation : translations) {
                    if(translation.split(":").length >= 2) {
                        String lang = translation.split(":")[0];
                        String value = translation.split(":")[1];

                        if (lang.equals(Language)) {
                            return value.replace("Â", "");
                        }
                    } else Main.plugin.getLogger().info("translation Error in translation " + input);
                }
                }
            }
        }
        if(languageType != LanguageType.English) return Translate(input,LanguageType.English);
        else return input;
    }

    public String Translate(String input) {
        return Translate(input,Main.Language);
    }


}
