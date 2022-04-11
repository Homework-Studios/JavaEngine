package net.rebix.engine.crafting;

import net.rebix.engine.item.EngineItem;
import org.bukkit.Material;

import java.util.Objects;

public class CraftingRecipe {
    private EngineItem result;
    private EngineItem[][] grid;




    public CraftingRecipe(EngineItem result, EngineItem[][] grid) {
        this.result = result;
        this.grid = grid;
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
    }

}

