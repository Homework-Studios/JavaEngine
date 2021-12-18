package net.rebix.engine.util;

import net.rebix.engine.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class cfgManager {

    public FileConfiguration Cfg = Main.plugin.getConfig();


    public cfgManager(){

    }

    public void enable(){

        Main.plugin.getConfig();
        Main.plugin.saveDefaultConfig();
    }

    public void writeDefaultCfg(){
        String defaultCfg;
        try {

            StringBuilder sb = new StringBuilder();
            for(Scanner sc = new Scanner(new URL("https://raw.githubusercontent.com/Homework-Studios/github-storage/main/JavaEngine/DefaultCfg").openStream()); sc.hasNext(); )
                sb.append(sc.nextLine()).append('\n');
            defaultCfg = sb.toString();

            FileWriter fileWriter = new FileWriter(new File(Main.plugin.getDataFolder(),"config.yml"),false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(defaultCfg);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
