package net.rebix.engine.util.api.packets;






import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class EntityHider{
    private Entity entity;
    public EntityHider(Entity entity){

        this.entity = entity;
    }


    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void hideEntity(){

    }

    public void hideEntityForPlayer(Player player){
        PlayerConnection connection = ((CraftPlayer) player).getHandle().b;


    }

    public void reloadHidden(){

    }
}
