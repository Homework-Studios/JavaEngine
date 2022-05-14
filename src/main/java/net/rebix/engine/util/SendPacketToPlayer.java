package net.rebix.engine.util;


import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SendPacketToPlayer {

    public SendPacketToPlayer(Player player, Packet packet) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
        connection.a(packet);
    }
}
