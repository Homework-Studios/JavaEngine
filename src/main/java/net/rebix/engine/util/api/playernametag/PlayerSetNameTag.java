package net.rebix.engine.util.api.playernametag;

import net.rebix.engine.util.api.packets.EntityHider;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;


import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class PlayerSetNameTag {
    private ArmorStand $laststand;
    private Boolean startrow = true;
    private AreaEffectCloud placeholder;

    public PlayerSetNameTag(Player $player, List<String> $nametag) {
        RemoveNameTag($player);
        getLogger().info("setze den spieler nametag auf"+$nametag);
        for (String $nametagrow: $nametag){
            if(startrow) {
                placeholder = (AreaEffectCloud) $player.getWorld().spawnEntity($player.getLocation(), EntityType.AREA_EFFECT_CLOUD);
                placeholder.setDuration(2000000000);
                placeholder.setParticle(Particle.ELECTRIC_SPARK,0);
                placeholder.setRadius(0);
                $player.setPassenger(placeholder);
                placeholder.addScoreboardTag($player.getName() + "_NameTag");
                startrow = false;
            } else {
                placeholder = (AreaEffectCloud) $player.getWorld().spawnEntity($player.getLocation(), EntityType.AREA_EFFECT_CLOUD);
                placeholder.setDuration(2000000000);
                placeholder.setParticle(Particle.ELECTRIC_SPARK,0);
                placeholder.setRadius(0);
                $laststand.setPassenger(placeholder);
                placeholder.addScoreboardTag($player.getName() + "_NameTag");
            }
            ArmorStand tag = (ArmorStand) $player.getWorld().spawnEntity($player.getLocation(), EntityType.ARMOR_STAND);
            tag.setGravity(false);
            tag.setMarker(true);
            tag.setInvisible(true);
            tag.setCustomNameVisible(true);
            tag.setCustomName($nametagrow);
            placeholder.setPassenger(tag);
            tag.addScoreboardTag($player.getName() + "_NameTag");
            $laststand = tag;


            for (Player splayer: Bukkit.getOnlinePlayers()){
                new EntityHider(placeholder).hideEntityForPlayer(splayer);
            }


        }
        startrow = true;
    }

    public void RemoveNameTag(Player $player){
        for(Entity $entity: $player.getWorld().getEntities()){
            if($entity.getScoreboardTags().contains($player.getName()+"_NameTag")) $entity.remove();
        }
    }
}
