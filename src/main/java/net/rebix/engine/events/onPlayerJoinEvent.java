package net.rebix.engine.events;

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
        TabListAPI tabListAPI = new TabListAPI(event.getPlayer(),"§fYour name: \n§f" + event.getPlayer().getName(),"§fYour Ping: " + event.getPlayer().getPing());
        tabListAPI.send();

        List<String> name = new ArrayList<>();
        name.add("1");
        name.add("2");
        name.add("3");
        name.add("4");
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
