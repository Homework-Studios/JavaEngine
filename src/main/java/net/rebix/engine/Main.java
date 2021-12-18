package net.rebix.engine;

import net.rebix.engine.util.Registry;
import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.api.playernametag.PlayerSetNameTag;
import net.rebix.engine.util.enums.LanguageType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {
    public static Integer INTEGER_LIMIT = 2147483647;
    public static LanguageType defaultLanguage = LanguageType.English;

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        new Registry();
        for(Player player: Bukkit.getOnlinePlayers()){



            List<String> nametag = new ArrayList<>();
            nametag.add("1");
            nametag.add("2");
            nametag.add("3");
            nametag.add("4");
            new PlayerSetNameTag(player,nametag);
        }


        //https://raw.githubusercontent.com/Reebix/Engine/master/CurrenVersion
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




}
