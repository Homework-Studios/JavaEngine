package net.rebix.engine.events;

import net.rebix.engine.Main;
import net.rebix.engine.api.packets.EntityHider;
import net.rebix.engine.api.playernametag.PlayerSetNameTag;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class onPlayerJoinEvent implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        List<String> name = new ArrayList<>();
        name.add("1");
        name.add("2");
        name.add("3");
        name.add("4");
        new PlayerSetNameTag(event.getPlayer(), name);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
            for (Entity entity: event.getPlayer().getWorld().getEntities()){
                if(entity.getScoreboardTags().contains(event.getPlayer().getName()+"_NameTag")){
                    new EntityHider(entity).hideEntity();
                }
            }
        }, 5L);



    }
}
