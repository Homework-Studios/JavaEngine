package net.rebix.engine.crafting;

import net.rebix.engine.item.EngineItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CraftingRecipe {
    private EngineItem result;
    private List<EngineItem> ingredients = new ArrayList<>();


    public CraftingRecipe(EngineItem result, List<EngineItem> ingredients) {
        this.result = result;
        this.ingredients = ingredients;


        save();
    }

    public CraftingRecipe(ItemStack result, List<Material> ingredients) {
        this.result = new EngineItem(result);
        for (int i = 0; i <= 8; i++) {
            if (ingredients.get(i) != null)
                this.ingredients.add(new EngineItem(new ItemStack(ingredients.get(i))));
            else this.ingredients.add(null);
        }
        save();
    }


    public EngineItem getResult() {
        return result;
    }

    public List<EngineItem> getIngredients() {

        return ingredients;
    }

    private void save() {

    }

    public void register() {
        CraftingManager.recipes.add(this);
    }
}

