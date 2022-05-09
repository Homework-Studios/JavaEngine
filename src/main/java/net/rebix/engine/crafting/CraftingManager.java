package net.rebix.engine.crafting;

import net.rebix.engine.Main;
import net.rebix.engine.api.property.ItemProperties;
import net.rebix.engine.events.customevents.ButtonClickEvent;
import net.rebix.engine.item.EngineItem;
import net.rebix.engine.item.ItemBuilder;
import net.rebix.engine.item.ItemFactory;
import net.rebix.engine.item.items.NullItem;
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
            List<EngineItem> items = new ArrayList<>();
            List<EngineItem> emptycheck = new ArrayList<>();
                for (int i = 0; i < inventory.getSize(); i++) {
                    if (inventory.getItem(i) != null) items.add(new EngineItem(inventory.getItem(i)));
                    else items.add(new NullItem());
                    if (inventory.getItem(i) != null &&! new ItemProperties(inventory.getItem(i)).getCannotBePickedUp()) emptycheck.add(new EngineItem(inventory.getItem(i)));
                }
            if(emptycheck.size() > 1) {
                EngineItem[][] inventoryIngredients = new EngineItem[][]{
                        {items.get(10), items.get(11), items.get(12), new NullItem(), new NullItem()},
                        {items.get(19), items.get(20), items.get(21), new NullItem(), new NullItem()},
                        {items.get(28), items.get(29), items.get(30), new NullItem(), new NullItem()},
                        {new NullItem(), new NullItem(), new NullItem(), new NullItem(), new NullItem()},
                        {new NullItem(), new NullItem(), new NullItem(), new NullItem(), new NullItem()}
                };
                if (player.getOpenInventory().getTitle().equals("CraftingTable 5x5")) {
                    inventoryIngredients = new EngineItem[][]{
                    {items.get(0), items.get(1), items.get(2), items.get(3), items.get(4)},
                    {items.get(9), items.get(10), items.get(11), items.get(12), items.get(13)},
                    {items.get(18), items.get(19), items.get(20), items.get(21), items.get(22)},
                    {items.get(27), items.get(28), items.get(29), items.get(30), items.get(31)},
                    {items.get(36), items.get(37), items.get(38), items.get(39), items.get(40)}
                };




                }


                EngineItem[][] finalInventoryIngredients = inventoryIngredients;
                CraftingRecipe inventoryRecipe = new CraftingRecipe(new NullItem(), finalInventoryIngredients);
                if (inventoryRecipe.topLeftCorner.getX() > 0 || inventoryRecipe.topLeftCorner.getY() > 0)
                    inventoryRecipe.normalize();
                inventory.setItem(24, ItemFactory.Items.get("NOTHING"));
                recipes.forEach(recipe -> {
                            if (recipe.compare(inventoryRecipe)) {
                                result(recipe.getResult(), player);
                            }
                        }
                );
            }
        }).start();
    }

    public void result(EngineItem item, Player player) {

        player.getOpenInventory().getTopInventory().setItem(24, new ItemBuilder(item).setButtonAction("craft").setPickupabel(false).build());

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
                List<EngineItem> engineItems = new ArrayList<EngineItem>();
                while (materialList.size() < 9) materialList.add(null);
                for (int i = 0; i < 9; i++) {
                        if(materialList.get(i) == null) engineItems.add(new NullItem());
                        else engineItems.add(new EngineItem(materialList.get(i)));
                }
                EngineItem[][] ingredients = new EngineItem[][]{
                    {engineItems.get(0),engineItems.get(1),engineItems.get(2),new NullItem(),new NullItem()},
                    {engineItems.get(3),engineItems.get(4),engineItems.get(5),new NullItem(),new NullItem()},
                    {engineItems.get(6),engineItems.get(7),engineItems.get(8),new NullItem(),new NullItem()},
                    {new NullItem(),new NullItem(),new NullItem(),new NullItem(),new NullItem()},
                    {new NullItem(),new NullItem(),new NullItem(),new NullItem(),new NullItem()}
                };
                new CraftingRecipe(new EngineItem(shapedRecipe.getResult()), ingredients).register();

            }
        });
    }




}
