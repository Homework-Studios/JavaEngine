package net.rebix.engine.commands;

import net.rebix.engine.api.Translator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TranslateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            if(args.length == 1)
                sender.sendMessage(new Translator().Translate(args[0]));
        return false;
    }
}
