package dev.trobanko.majesticlobby.Commands;

import dev.trobanko.majesticlobby.ConfigHandler.WorldConfig;
import dev.trobanko.majesticlobby.MajesticLobby;
import dev.trobanko.majesticlobby.Utils.Colorize;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class ReloadConfigCommand implements CommandExecutor{

    MajesticLobby plugin;

    public ReloadConfigCommand(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            plugin.reloadConfig();
            plugin.getLogger().log(Level.FINEST, "Re-loaded Config Successfully");
            return true;
        }
        Player player = (Player) sender;
        if(!player.hasPermission("majesticlobby.admin")){
            player.sendMessage(Colorize.Color("&cYou do not have the permission to do that."));
            return true;
        }
        plugin.reloadConfig();
        WorldConfig.reload();
        player.sendMessage(Colorize.Color("&aSuccessfully re-loaded the config!"));
        return true;
    }
}
