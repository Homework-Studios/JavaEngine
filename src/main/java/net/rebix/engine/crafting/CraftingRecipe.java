package net.rebix.engine.crafting;

import net.rebix.engine.item.EngineItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CraftingRecipe {
    private EngineItem result;
    private List<EngineItem> ingredients;
    private int size;
    private RecipeType type;

    public CraftingRecipe(RecipeType type,EngineItem result, List<EngineItem> ingredients, int size) {
        this.result = result;
        this.ingredients = ingredients;
        this.size = size;
        this.type = type;

        save(type.toString());
    }

    public CraftingRecipe(RecipeType type, Material result, List<Material> ingredients, int size) {
        this.result = new EngineItem(new ItemStack(result));
        for (int i = 0; i < 7; i++) this.ingredients.add(new EngineItem(new ItemStack(ingredients.get(i))));
        this.size = size;
        this.type = type;

        save(type.toString());
    }


    public EngineItem getResult() {
        return result;
    }

    public List<EngineItem> getIngredients() {
        return ingredients;
    }

    public int getSize() {
        return size;
    }

    public RecipeType getType() {
        return type;
    }


    private void save(String type) {
    	String save = type;

    }
}

