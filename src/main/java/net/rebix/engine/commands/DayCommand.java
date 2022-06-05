package net.rebix.engine.commands;

import net.rebix.engine.api.CommandPermissionManager;
import net.rebix.engine.api.Translator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!new CommandPermissionManager().checkPermissionLevelOfPlayer(player,label)) return false;
            player.getWorld().setTime(100);
            player.getWorld().setStorm(false);
            player.getWorld().setThundering(false);
        } else {
            if(args.length > 0) {
                World world = Bukkit.getWorld(args[0]);
                if(world != null) {
                    world.setTime(100);
                    world.setStorm(false);
                    world.setThundering(false);
                } else sender.sendMessage(new Translator().Translate("engine.arguments.wrong"));
            } else sender.sendMessage(new Translator().Translate("engine.arguments.missing"));
        }

        return false;
    }


}
