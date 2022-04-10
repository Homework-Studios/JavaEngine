package net.rebix.engine.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if(sender.hasPermission("engine.command.spawn.use"))
        if(args[0].length() == 0) player.getWorld().spawnEntity(player.getLocation(), EntityType.valueOf(args[0]));
        else
        for(int $index = 0; $index < Integer.parseInt(args[1]); ++$index){
            player.getWorld().spawnEntity(player.getLocation(), EntityType.valueOf(args[0]));
        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> out = new ArrayList<>();

        for (EntityType e: EntityType.values()) {
            if(String.valueOf(e).startsWith(args[0]))
            out.add(String.valueOf(e));
            if(String.valueOf(e).equals(args[0])) return null;
        }
        return out;
    }
}
