package net.rebix.engine.api.WorldManager;

import net.rebix.engine.Main;
import net.rebix.engine.api.ScrollableInventory;
import net.rebix.engine.item.ItemBuilder;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldManager {


        List<WorldManagerWorld> worlds = new ArrayList<>();

        public void joinByName(Player player, String Name) {
                if(new File(Main.plugin.getDataFolder().getAbsolutePath().replace("plugins\\JavaEngine",Name)).exists()) {
                        World world = Bukkit.createWorld(new WorldCreator(Name));
                        player.teleport(new Location(world,0,320,0));
                        assert world != null;
                        world.setType(new Location(world,0,320,0), Material.GLASS);
                }
        }

        public void loadFromTemplate(String templateName, String newName) {
                World template = Bukkit.createWorld(new WorldCreator(templateName + "_template_"));
                new File(template.getWorldFolder().getAbsolutePath() + "\\" + templateName).delete();
                try {
                        FileUtils.copyDirectory(template.getWorldFolder(), new File(template.getWorldFolder().getParent(), templateName));
                        World newWorld = Bukkit.createWorld(new WorldCreator(newName));
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        public void openMenu(Player player) {
                ScrollableInventory inventory = new ScrollableInventory().create(player,"§aWorlds",6*9,1);
                HashMap<Integer,ItemStack> iinvitem = new HashMap<>();
                for (int i = 0; i < worlds.size(); i++) {
                        iinvitem.put(i,new ItemBuilder(Material.GRASS_BLOCK).setLocalName(worlds.get(i).getName()).setGlowing(true).setButtonAction("BUTTON_ACTION.WORLD_MANAGER.OPEN:" + worlds.get(i).getName()).build());
                }
                iinvitem.put(worlds.size() +1,new ItemBuilder(Material.GREEN_CONCRETE).setName("§aAdd").setButtonAction("BUTTON_ACTION.WORLD_MANAGER.ADD").build());
                inventory.setContents(iinvitem);

        }

        public void addWorld(String name) {
                worlds.add(new WorldManagerWorld(name));
        }

        public void removeWorld(String name) {
                for (WorldManagerWorld world : worlds) {
                        if(world.getName().equals(name)) {
                                worlds.remove(world);
                                return;
                        }
                }
        }

}
