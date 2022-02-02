package net.rebix.engine.events.player;

import net.rebix.engine.Main;
import net.rebix.engine.api.packets.EntityHider;
import net.rebix.engine.api.packets.TabListAPI;
import net.rebix.engine.api.playernametag.PlayerSetNameTag;
import net.rebix.engine.api.Translator;
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
        event.setJoinMessage(new Translator().Translate("engine.player.join").replaceAll("_"," ").replace("//player//",event.getPlayer().getDisplayName()));


        List<String> name = new ArrayList<>();
        name.add(event.getPlayer().getDisplayName());
        if(Main.plugin.getConfig().getBoolean("Nametag"))
        new PlayerSetNameTag(event.getPlayer(), name);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {
            for (Entity entity: event.getPlayer().getWorld().getEntities()){
                if(entity.getScoreboardTags().contains(event.getPlayer().getName()+"_NameTag")){
                    new EntityHider(entity).HideEntity();
                }
            }
        }, 10L);



    }
}
