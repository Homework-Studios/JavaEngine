package net.rebix.engine.api.packets;


import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;

import net.minecraft.server.network.PlayerConnection;
import net.rebix.engine.util.SendPacketToPlayer;
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

        PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(entity.getEntityId());
        new SendPacketToPlayer(player,packetPlayOutEntityDestroy);

    }

}
