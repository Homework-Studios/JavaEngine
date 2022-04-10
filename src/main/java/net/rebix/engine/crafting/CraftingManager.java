package net.rebix.engine.crafting;

import net.rebix.engine.Main;
import net.rebix.engine.events.customevents.ButtonClickEvent;
import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import net.rebix.engine.item.ItemFactory;
import net.rebix.engine.item.items.Bedrock;
import net.rebix.engine.item.items.TestItem;
import net.rebix.engine.util.FillInventoryWithPlaceholder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CraftingManager implements Listener {
    public static List<CraftingRecipe> recipes = new ArrayList<CraftingRecipe>();


    public CraftingManager() {
    }



    public CraftingRecipe getRecipe(EngineItem itemStack) {
        for (CraftingRecipe recipe : recipes) {
            if (recipe.getResult().getId().equals(itemStack.getId())) {
                return recipe;
            }
        }
        return null;
    }

    public CraftingRecipe getRecipeByIngredients(List<EngineItem> items) {
        for (CraftingRecipe recipe : recipes) {
            StringBuilder ingredients = new StringBuilder();
            StringBuilder ingredients2 = new StringBuilder();
            for (EngineItem item : items)  if(item != null) ingredients2.append(item.getId()); else ingredients2.append("null");
            for (EngineItem item : recipe.getIngredients()) if(item != null) ingredients.append(item.getId()); else ingredients.append("null");
            if (ingredients.toString().equals(ingredients2.toString())) {
                return recipe;
            }
        }
        return null;
    }

    public void openMenu(Player player) {
        open3x3(player);

    }

    public void open3x3(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9 * 5, "CraftingTable 3x3");
        new FillInventoryWithPlaceholder(inventory);
        setCraftingInventoryDefaults(inventory, 5);
        for (int id = 1; id <= 3; id++)
            for (int i = 1 + id*9; i < 4 + id*9; i++) inventory.setItem(i, null);
        player.openInventory(inventory);

    }
    public void open5x5(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9*5, "CraftingTable 5x5");
        new FillInventoryWithPlaceholder(inventory);
        setCraftingInventoryDefaults(inventory, 3);
        for (int id = 0; id <= 4; id++)
            for (int i = 0 + id*9; i < 5 + id*9; i++) inventory.setItem(i, null);
        player.openInventory(inventory);
    }

    private void setCraftingInventoryDefaults(Inventory inventory, int size) {
        inventory.setItem(42, ItemFactory.Items.get("EXIT_BUTTON"));
        inventory.setItem(24,ItemFactory.Items.get("NOTHING"));
        for (int i = 1; i < 6; i++) inventory.setItem(i*9-1,  new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§4Quick Craft").setPickupabel(false).build());
        inventory.setItem(6,new ItemBuilder(Material.CRAFTING_TABLE).setName("§6"+size+"x"+size).setButtonAction(size+"x"+size).build());
    }

    public void craftingUpdate(Player player) {
        new Thread(() -> {
            Inventory inventory = player.getOpenInventory().getTopInventory();
            List<EngineItem> items = new ArrayList<EngineItem>();

            if (player.getOpenInventory().getTitle().contains("3x3")) {
                for (int id = 1; id <= 3; id++)
                    for (int i = 1 + id*9; i < 4 + id*9; i++) if (inventory.getItem(i) != null) items.add(new EngineItem(inventory.getItem(i))); else items.add(null);
            } else {
                for (int id = 0; id <= 4; id++)
                    for (int i = 0 + id*9; i < 5 + id*9; i++) if (inventory.getItem(i) != null) items.add(new EngineItem(inventory.getItem(i))); else items.add(null);
            }
            CraftingRecipe result = getRecipeByIngredients(items);
            if(result != null) player.getOpenInventory().getTopInventory().setItem(24,result.getResult().getItem());
            else inventory.setItem(24,ItemFactory.Items.get("NOTHING"));
        }).start();
    }

    @EventHandler
    public void ButtonClickEvent(ButtonClickEvent event) {
        if(event.getPlayer().getOpenInventory().getTitle().contains("CraftingTable")) {
            switch (event.getButtonAction()) {
                case "3x3":
                    open3x3(event.getPlayer());
                    break;
                case "5x5":
                    open5x5(event.getPlayer());
                    break;
            }
        }
    }


    public void updateRecipes() {
        Main.plugin.getServer().recipeIterator().forEachRemaining(recipe -> {
            if(recipe instanceof ShapedRecipe) {
                ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
                List<String> charMaterialArray = new ArrayList<String>();
                List<Material> materialList = new ArrayList<Material>();
                Map<Character, ItemStack> charMap = shapedRecipe.getIngredientMap();

                for (String s : shapedRecipe.getShape()) {
                    char[] chars = s.toCharArray();
                    for (int i = 0; i < 3; i++) {
                        if(chars.length > i) {
                        charMaterialArray.add(chars[i] + "");
                        } else {
                            charMaterialArray.add(null);
                        }
                    }
                }
                for(String s : charMaterialArray) {
                    Material material = null;
                    if(s != null) if(s.charAt(0) != ' ') if(charMap.get(s.charAt(0)) != null)
                        material = charMap.get(s.charAt(0)).getType();
                    materialList.add(material);
                }
                while (materialList.size() < 9) materialList.add(null);
                new CraftingRecipe(shapedRecipe.getResult(), materialList).register();

            }
        });
    }
}
