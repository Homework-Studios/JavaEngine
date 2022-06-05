package net.rebix.engine;

import net.rebix.engine.api.CommandPermissionManager;
import net.rebix.engine.api.Translator;
import net.rebix.engine.commands.ItemCommand;
import net.rebix.engine.commands.WriteDefaultCfgCommand;
import net.rebix.engine.crafting.CraftingManager;
import net.rebix.engine.crafting.CraftingRecipe;
import net.rebix.engine.discordchatbot.ChatBot;
import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import net.rebix.engine.item.ItemFactory;
import net.rebix.engine.item.items.Bedrock;
import net.rebix.engine.item.items.TestItem;
import net.rebix.engine.item.items.engineitems.*;
import net.rebix.engine.util.Registry;
import net.rebix.engine.util.TickingTask10timesperseckond;
import net.rebix.engine.util.cfgManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    public static Integer INTEGER_LIMIT = 2147483647;
    public static String Language = "English";
    static CraftingManager craftingManager = new CraftingManager();
    static ItemFactory itemFactory = new ItemFactory();
    public static ChatBot CHATBOT;

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        if(!new File(this.getDataFolder(),"config.yml").exists()) new WriteDefaultCfgCommand().write();

        //Removing unwanted Entity's with scoreboard Tag: RemoveEntityOnDisable
        for (World world: Bukkit.getWorlds())
            for (Entity entity: world.getEntities())
                if(entity.getScoreboardTags().contains("RemoveEntityOnDisable")) entity.remove();

        new Registry();


        new Translator().enable();
        new cfgManager().enable();
        itemFactory.enable();
        new TickingTask10timesperseckond();
        new ItemCommand().enable();
        new CommandPermissionManager().enable();

        Main.Language = Main.plugin.getConfig().getString("Language");


       new CraftingRecipe(new TestItem(), new EngineItem[][]{
               {new Bedrock(), new Bedrock(), new Bedrock(), new Bedrock(), new Bedrock()},
               {null, null, new Bedrock(), null, null},
               {null, null, new Bedrock(), null, null},
               {null, null, new Bedrock(), null, null},
               {null, null, new Bedrock(), null, null}
       }).register();

       EngineItem netherstar = new EngineItem(new ItemBuilder(Material.NETHER_STAR).setAmount(32));

       new CraftingRecipe(new ENGINE_ITEM_ENCHANTED_NETHER_STAR(), new EngineItem[][]{
               {netherstar, netherstar, netherstar, null, null},
               {netherstar, netherstar, null, null, null},
               {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
       }).register();

        EngineItem ens = new ENGINE_ITEM_ENCHANTED_NETHER_STAR().setAmount(32);
       new CraftingRecipe(new ENCHANTED_NETHER_STAR_BLOCK(), new EngineItem[][]{
               {ens, ens, ens, null, null},
               {ens, ens, null, null, null},
               {null, null, null, null, null},
               {null, null, null, null, null},
               {null, null, null, null, null}
       }).register();
        EngineItem ei = new ENGINE_ITEM_ENCHANTED_IRON().setAmount(32);
        new CraftingRecipe(new ENGINE_ITEM_ENCHANTED_IRON_BLOCK(), new EngineItem[][]{
                {ei, ei, ei, null, null},
                {ei, ei, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
        }).register();
        EngineItem i = new EngineItem(new ItemBuilder(Material.IRON_INGOT).setAmount(32));
        new CraftingRecipe(new ENGINE_ITEM_ENCHANTED_IRON(), new EngineItem[][]{
                {i, i, i, null, null},
                {i, i, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
        }).register();

        EngineItem ed = new ENGINE_ITEM_ENCHANTED_DIAMOND().setAmount(32);
        new CraftingRecipe(new ENGINE_ITEM_ENCHANTED_DIAMOND_BLOCK(), new EngineItem[][]{
                {ed, ed, ed, null, null},
                {ed, ed, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
        }).register();
        EngineItem d = new EngineItem(new ItemBuilder(Material.DIAMOND).setAmount(32));
        new CraftingRecipe(new ENGINE_ITEM_ENCHANTED_DIAMOND(), new EngineItem[][]{
                {d, d, d, null, null},
                {d, d, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
        }).register();

        EngineItem nsb = new ENCHANTED_NETHER_STAR_BLOCK();
        new CraftingRecipe(new ENGINE_ITEM_TEST_FURY(), new EngineItem[][]{
                {null, null, null, nsb, nsb},
                {null, null, nsb, nsb, nsb},
                {new ENGINE_ITEM_ENCHANTED_IRON_BLOCK(), nsb, nsb, nsb, null},
                {null, new ENGINE_ITEM_ENCHANTED_DIAMOND_BLOCK(), nsb, null, null},
                {new EngineItem(new ItemBuilder(Material.STICK)), null, new ENGINE_ITEM_ENCHANTED_IRON_BLOCK(), null, null}
        }).register();

       craftingManager.updateRecipes();
        Bukkit.getLogger().info(new Translator().Translate("engine.load"));

        if(this.getConfig().getBoolean("ChatBot")) CHATBOT = new ChatBot();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getVersion(){
        return plugin.getDescription().getVersion();
    }
    public static CraftingManager getCraftingManager(){
        return craftingManager;
    }
    public static ItemFactory getItemFactory(){
        return itemFactory;
    }

}
