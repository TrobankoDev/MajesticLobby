package dev.trobanko.majesticlobby.Commands;

import dev.trobanko.majesticlobby.ConfigHandler.WorldConfig;
import dev.trobanko.majesticlobby.MajesticLobby;
import dev.trobanko.majesticlobby.Utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.logging.Level;

public class SetSpawnCommand implements CommandExecutor {

    MajesticLobby plugin;

    public SetSpawnCommand(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if(!player.hasPermission("majesticlobby.admin")) return true;
        Location loc = player.getLocation();

        String worldName = plugin.getConfig().getString("lobby-world");

        try{
            Bukkit.getServer().getWorld(worldName).setSpawnLocation(loc);
        } catch (NullPointerException e) {
            plugin.getLogger().log(Level.WARNING, "The world " + worldName + " is invalid! Please update the config.yml");
        }

        ArrayList<Double> locationCords = new ArrayList<>();
        locationCords.add(loc.getX());
        locationCords.add(loc.getY());
        locationCords.add(loc.getZ());
        locationCords.add((double) loc.getYaw());
        locationCords.add((double) loc.getPitch());

        WorldConfig.get().set("world.spawn", locationCords);
        WorldConfig.save();
        WorldConfig.reload();


        player.sendMessage(Colorize.Color("&aSuccessfully set world spawn to this location!"));
        return true;
    }
}
