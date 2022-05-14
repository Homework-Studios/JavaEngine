package net.rebix.engine.api;

import net.rebix.engine.Main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Translator {

    public static List<String> TranslateList = new ArrayList<>();

    public void enable() {
        StringBuilder sb = new StringBuilder();
        try {
            if(!new File(Main.plugin.getDataFolder() + File.separator + "Translations.txt").exists()) {
                PrintWriter writer = new PrintWriter(Main.plugin.getDataFolder() + "/Translations.txt");
                writer.println("test");
            }
            for(Scanner scanner = new Scanner(new URL(Objects.requireNonNull(Main.plugin.getConfig().getString("DigitalTranslationTable"))).openStream()); scanner.hasNext(); )
                TranslateList.add(scanner.nextLine());
            for(Scanner scanner = new Scanner(new File(Main.plugin.getDataFolder() + File.separator + "Translations.txt")); scanner.hasNext(); )
                TranslateList.add(scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TranslateList.removeIf(line -> line.startsWith("#"));
    }

    public String Translate(String input, String Language) {
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
        if(!Objects.equals(Language, "English")) return Translate(input,"English");
        else return input;
    }

    public String Translate(String input) {
        return Translate(input,Main.Language);
    }


}
