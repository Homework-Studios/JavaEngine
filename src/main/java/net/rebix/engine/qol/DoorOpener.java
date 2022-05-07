package net.rebix.engine.qol;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

public class DoorOpener {

    public static void DoorUpdate(Block door) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(door.getRelative(i, 0, j).getType().equals(door.getType()))
               if(i != 0 || j != 0) {
                   Block otherdoor = door.getRelative(i, 0, j);
                   String newdata = "";
                   if(door.getBlockData().getAsString().contains("open=true")) newdata = otherdoor.getBlockData().getAsString().replaceAll("open=true", "open=false");
                   else newdata = otherdoor.getBlockData().getAsString().replaceAll("open=false", "open=true");
                   otherdoor.setBlockData(Bukkit.createBlockData(newdata));
                   }
               }
            }
    }
}
