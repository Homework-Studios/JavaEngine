package net.rebix.engine.util.api.playernametag;

import org.bukkit.Particle;
import org.bukkit.entity.*;

import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class PlayerSetNameTag {
    private ArmorStand last_armor_stand;

    public PlayerSetNameTag(Player $player, List<String> name) {
        RemoveNameTag($player);
        getLogger().info("setze den spieler nametag auf"+name);
        boolean startrow = true;
        for (String row: name){
            AreaEffectCloud placeholder;
            placeholder = (AreaEffectCloud) $player.getWorld().spawnEntity($player.getLocation(), EntityType.AREA_EFFECT_CLOUD);
            placeholder.setDuration(2000000000);
            placeholder.setParticle(Particle.ELECTRIC_SPARK,0);
            placeholder.setRadius(0);
            if(startrow) {
                $player.addPassenger(placeholder);
                placeholder.addScoreboardTag($player.getName() + "_NameTag");
                placeholder.addScoreboardTag("hidden");
                startrow = false;
            } else {
                last_armor_stand.addPassenger(placeholder);
                placeholder.addScoreboardTag($player.getName() + "_NameTag");
                placeholder.addScoreboardTag("hidden");
            }
            ArmorStand tag = (ArmorStand) $player.getWorld().spawnEntity($player.getLocation(), EntityType.ARMOR_STAND);
            tag.setGravity(false);
            tag.setMarker(true);
            tag.setInvisible(true);
            tag.setCustomNameVisible(true);
            tag.setCustomName(row);
            placeholder.addPassenger(tag);
            tag.addScoreboardTag($player.getName() + "_NameTag");
            last_armor_stand = tag;


        }
    }

    public void RemoveNameTag(Player $player){
        for(Entity $entity: $player.getWorld().getEntities()){
            if($entity.getScoreboardTags().contains($player.getName()+"_NameTag")) $entity.remove();
        }
    }
}
