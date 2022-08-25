package net.rebix.engine;

import org.bukkit.GameRule;
import org.bukkit.entity.Player;

import java.util.HashMap;


public class EPlayer {

    Player player;
    Integer maxHealth = 500;
    Integer health = maxHealth;


    public static HashMap<String, EPlayer> players = new HashMap<>();

    public EPlayer(Player player) {
        this.player = player;
        players.put(player.getUniqueId().toString(),this);
        player.setMaximumNoDamageTicks(0);
        convertHealth();
    }

    public static EPlayer get(Player player) {
        if (players.containsKey(player.getUniqueId().toString())) {
            return players.get(player.getUniqueId().toString());
        } else {
            return new EPlayer(player);
        }
    }


    public Player getPlayer() {
        return player;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
        convertHealth();
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
        convertHealth();
    }

    public void addHealth(Integer health) {
        this.health += health;
        if(this.health > maxHealth) {
            this.health = maxHealth;
        }
        convertHealth();
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }


    public void convertHealth() {
        char[] chars = health.toString().toCharArray();
        if(health < 1) { respawn(); return; }
        Integer redHealth = 20;
        Integer maxRedHealth = 20;

        if(health < 500)
            redHealth = health /25;
        if(maxHealth < 500)
            maxRedHealth = maxHealth / 25;
        Integer hp = health-500;
        Integer goldHealth = 0;
        //calculatingGold
        if(hp > 0) goldHealth = hp / 100;
        player.setHealthScale(maxRedHealth);
        player.setHealth(redHealth);
        player.setAbsorptionAmount(goldHealth);

    }

    public void respawn() {
        setHealth(maxHealth);
    }
}
