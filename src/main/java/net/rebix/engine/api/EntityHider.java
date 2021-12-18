package net.rebix.engine.api;


import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;

import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
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

    public void HideEntity(){
        for (Player player: Bukkit.getOnlinePlayers()) HideEntityForPlayer(player);
    }

    public void HideEntityForPlayer(Player player){
        PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
        PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(entity.getEntityId());
        connection.a(packetPlayOutEntityDestroy);

    }

    public void reloadHidden(){

    }
}
