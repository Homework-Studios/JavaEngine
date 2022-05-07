package net.rebix.engine.crafting;

import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.items.NullItem;
import net.rebix.engine.util.variables.Vector2;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;




public class CraftingRecipe {
    private EngineItem result;
    private EngineItem[][] grid;
    private HashMap<EngineItem, Integer> ingredients = new HashMap<>();
    public Vector2 bottomRightCorner = new Vector2(0, 0);
    public Vector2 topLeftCorner = new Vector2(0, 0);
    private boolean shaped = false;


    void checkCorner() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] != null)
                if (grid[i][j].getId() != new NullItem().getId()) {
                    if (i > bottomRightCorner.getX()) bottomRightCorner.setX(j);
                    if (j > bottomRightCorner.getY()) bottomRightCorner.setY(i);
                }

                if (grid[i][j] != null)
                if (grid[i][j].getId() != new NullItem().getId()) {
                    if (i < topLeftCorner.getX()) topLeftCorner.setX(j);
                    if (j < topLeftCorner.getY()) topLeftCorner.setY(i);
                }
            }
        }
        ;
    }

    public CraftingRecipe(EngineItem result, HashMap<EngineItem,Integer> ingredients) {
        this.result = result;
        this.ingredients = ingredients;

       checkCorner();
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
        checkCorner();
    }

    void addIngredient(EngineItem item) {
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

    public boolean compareIngredients(HashMap<EngineItem, Integer> ingredients) {
        for (EngineItem item : ingredients.keySet()) {
            if (!this.ingredients.containsKey(item) || this.ingredients.get(item) != ingredients.get(item)) return false;
        }
        return true;
    }

    public boolean compare(CraftingRecipe recipe) {
        return false;
    }



    public boolean isShaped() {
        return shaped;
    }

    public HashMap<EngineItem, Integer> getIngredients() {
        return ingredients;
    }
}

