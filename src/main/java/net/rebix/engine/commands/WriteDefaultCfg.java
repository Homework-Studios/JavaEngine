package net.rebix.engine.commands;

import net.rebix.engine.Main;
import net.rebix.engine.util.Translator;
import net.rebix.engine.util.cfgManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;



public class WriteDefaultCfg implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            new cfgManager().writeDefaultCfg();
            Bukkit.getLogger().info(new Translator().Translate("engine.cfg.default", Main.defaultLanguage));
        }
        return false;
    }
}
