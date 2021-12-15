package net.rebix.engine.api.packets;


import org.bukkit.Bukkit;
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
        for (Player player: Bukkit.getOnlinePlayers()) hideEntityForPlayer(player);
    }

    public void hideEntityForPlayer(Player player){
        /*
        Deactivated
        PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
        PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(entity.getEntityId());
        connection.sendPacket(packetPlayOutEntityDestroy);
*/
    }

    public void reloadHidden(){

    }
}
