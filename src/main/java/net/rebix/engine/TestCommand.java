package net.rebix.engine;

import net.rebix.engine.api.packets.EntityHider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (Entity entity:((Player)sender).getWorld().getEntities()) if(entity.getScoreboardTags().contains((sender).getName()+"_NameTag")) new EntityHider(entity).hideEntity();
        return false;
    }
}
