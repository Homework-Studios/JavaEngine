package net.rebix.engine.crafting;

import net.rebix.engine.Main;
import net.rebix.engine.events.customevents.ButtonClickEvent;
import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import net.rebix.engine.item.ItemFactory;
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
    private List<CraftingRecipe> recipes = new ArrayList<CraftingRecipe>();

    public CraftingManager() {
    }

    public void registerRecipe(CraftingRecipe recipe) {
        recipes.add(recipe);
    }

    public CraftingRecipe getRecipe(EngineItem itemStack) {
        for (CraftingRecipe recipe : recipes) {
            if (recipe.getResult().equals(itemStack)) {
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
        for (int i = 10; i <= 12; i++) inventory.setItem(i, null);
        for (int i = 19; i <= 21; i++) inventory.setItem(i, null);
        for (int i = 28; i <= 30; i++) inventory.setItem(i, null);
        player.openInventory(inventory);
    }

    public void open5x5(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9*5, "CraftingTable 5x5");
        new FillInventoryWithPlaceholder(inventory);
        setCraftingInventoryDefaults(inventory, 3);
        for (int i = 0; i <= 4; i++) inventory.setItem(i, null);
        for (int i = 9; i <= 13; i++) inventory.setItem(i, null);
        for (int i = 18; i <= 22; i++) inventory.setItem(i, null);
        for (int i = 27; i <= 31; i++) inventory.setItem(i, null);
        for (int i = 36; i <= 40; i++) inventory.setItem(i, null);
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

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if(event.getWhoClicked().getOpenInventory().getTitle().contains("CraftingTable")) {

        }
    }

    public void updateRecipes() {
        new Thread(() -> {
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
                System.out.println(materialList);
            }
        });
        }).start();
    }
}
