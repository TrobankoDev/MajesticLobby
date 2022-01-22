package dev.trobanko.majesticlobby.Commands;

import dev.trobanko.majesticlobby.ConfigHandler.WorldConfig;
import dev.trobanko.majesticlobby.MajesticLobby;
import dev.trobanko.majesticlobby.Utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SpawnCommand implements CommandExecutor {

    MajesticLobby plugin;

    public SpawnCommand(MajesticLobby plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        double x =  WorldConfig.get().getDoubleList("world.spawn").get(0);
        double y =  WorldConfig.get().getDoubleList("world.spawn").get(1);
        double z =  WorldConfig.get().getDoubleList("world.spawn").get(2);
        double yaw =  WorldConfig.get().getDoubleList("world.spawn").get(3);
        double pitch =  WorldConfig.get().getDoubleList("world.spawn").get(4);

        String worldName = plugin.getConfig().getString("lobby-world");
        World world;
        try{
            world = Bukkit.getWorld(worldName);
        } catch (NullPointerException e) {
            plugin.getLogger().log(Level.WARNING, "The world " + worldName + " is invalid! Please update the config.yml");
            player.sendMessage(Colorize.Color("&cSomething went wrong, please contact the server administrator"));
            return true;
        }

        player.teleport(new Location(world, x, y, z, (float) yaw, (float) pitch));
        player.sendMessage(Colorize.Color("&aTeleported to spawn!"));

        return true;
    }
}
