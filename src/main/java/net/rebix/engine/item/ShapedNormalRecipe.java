package net.rebix.engine.item;

import net.rebix.engine.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ShapedNormalRecipe {

    public ShapedNormalRecipe(String recipeName, ItemStack itemStack, Material A, Material B, Material C, Material D, Material E, Material F, Material G, Material H, Material I) {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.plugin,recipeName), itemStack);
        recipe.shape("ABC","DEF","GHI");
        recipe.setIngredient('A',A);
        recipe.setIngredient('B',B);
        recipe.setIngredient('C',C);
        recipe.setIngredient('D',D);
        recipe.setIngredient('E',E);
        recipe.setIngredient('F',F);
        recipe.setIngredient('G',G);
        recipe.setIngredient('H',H);
        recipe.setIngredient('I',I);
        Bukkit.addRecipe(recipe);
    }
}
