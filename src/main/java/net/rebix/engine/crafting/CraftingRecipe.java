package net.rebix.engine.crafting;

import net.rebix.engine.item.EngineItem;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CraftingRecipe {
    private EngineItem result;
    private EngineItem[][] grid;
    private HashMap<EngineItem, Integer> ingredients = new HashMap<>();
    private boolean shaped = false;




    public CraftingRecipe(EngineItem result, HashMap<EngineItem,Integer> ingredients) {
        this.result = result;
        this.ingredients = ingredients;
    }

    public CraftingRecipe(EngineItem result, EngineItem[][] grid) {
        shaped = true;
        this.grid = grid;
        this.result = result;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                addIngredient(grid[i][j]);
            }
        }

    }

    private void addIngredient(EngineItem item) {
        if (ingredients.containsKey(item)) ingredients.put(item, ingredients.get(item) + 1);
        else ingredients.put(item, 1);
    }


    public EngineItem getResult() {
        return result;
    }



    public void register() {
        CraftingManager.recipes.add(this);
    }

    public EngineItem[][] getGrid() {
        return grid;
    }


    public boolean compare(EngineItem[][] grid) {
        if(shaped) {

                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (!Objects.equals(this.grid[i][j], null) && !Objects.equals(grid[i][j], null))
                            {
                                if (!Objects.equals(this.grid[i][j].getId(), grid[i][j].getId()))
                                    return false;
                            }
                        }
                    }
                    return true;
        } else
        {
            HashMap<EngineItem, Integer> ingredients = new HashMap<EngineItem, Integer>();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    EngineItem item = grid[i][j];
                    if (ingredients.containsKey(item)) ingredients.put(item, ingredients.get(item) + 1);
                    else ingredients.put(item, 1);
                }
            }
            return ingredients.equals(this.ingredients);
        }
    }



    public boolean isShaped() {
        return shaped;
    }

    public HashMap<EngineItem, Integer> getIngredients() {
        return ingredients;
    }
}

