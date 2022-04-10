package net.rebix.engine;

import net.rebix.engine.api.CommandPermissionManager;
import net.rebix.engine.api.Translator;
import net.rebix.engine.commands.ItemCommand;
import net.rebix.engine.commands.WriteDefaultCfgCommand;
import net.rebix.engine.crafting.CraftingManager;
import net.rebix.engine.crafting.CraftingRecipe;
import net.rebix.engine.crafting.RecipeType;
import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemFactory;
import net.rebix.engine.item.items.Bedrock;
import net.rebix.engine.item.items.TestItem;
import net.rebix.engine.util.Registry;
import net.rebix.engine.util.TickingTask10timesperseckond;
import net.rebix.engine.util.cfgManager;
import net.rebix.engine.util.enums.LanguageType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {
    public static Integer INTEGER_LIMIT = 2147483647;
    public static LanguageType Language = LanguageType.English;
    static CraftingManager craftingManager = new CraftingManager();
    static ItemFactory itemFactory = new ItemFactory();

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

        Main.Language = LanguageType.valueOf(Main.plugin.getConfig().getString("Language"));



        List<EngineItem> ingredients = new ArrayList<>();
        ingredients.add(new Bedrock());
        ingredients.add(new Bedrock());
        ingredients.add(new Bedrock());
        ingredients.add(new Bedrock());
        ingredients.add(null);
        ingredients.add(new Bedrock());
        ingredients.add(new Bedrock());
        ingredients.add(new Bedrock());
        ingredients.add(new Bedrock());
       craftingManager.registerRecipe(new CraftingRecipe(RecipeType.SHAPELESS, new TestItem(), ingredients,3));

       craftingManager.updateRecipes();
        Bukkit.getLogger().info(new Translator().Translate("engine.load"));
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
