package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.ConfigHandler.WorldConfig;
import dev.trobanko.majesticlobby.MajesticLobby;
import dev.trobanko.majesticlobby.Utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class PlayerJoinListener implements Listener {

    MajesticLobby plugin;

    public PlayerJoinListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(e.getPlayer().hasPermission("majesticlobby.bypass") || e.getPlayer().hasPermission("majesticlobby.admin")) return;
        if(!plugin.getConfig().getBoolean("clear-inventory-on-join")) return;
        e.getPlayer().getInventory().clear();
    }

    @EventHandler
    public void onPlayerJoinToTp(PlayerJoinEvent e){
        if(!plugin.getConfig().getBoolean("use-world-spawn")) return;
        Player player = e.getPlayer();
        double x =  WorldConfig.get().getDoubleList("world.spawn").get(0);
        double y =  WorldConfig.get().getDoubleList("world.spawn").get(1);
        double z =  WorldConfig.get().getDoubleList("world.spawn").get(2);
        double yaw =  WorldConfig.get().getDoubleList("world.spawn").get(3);
        double pitch =  WorldConfig.get().getDoubleList("world.spawn").get(4);

        String worldName = plugin.getConfig().getString("lobby-world");
        World world;
        try{
            world = Bukkit.getWorld(worldName);
        } catch (NullPointerException ex) {
            plugin.getLogger().log(Level.WARNING, "The world " + worldName + " is invalid! Please update the config.yml");
            player.sendMessage(Colorize.Color("&cSomething went wrong, please contact the server administrator"));
            return;
        }
        player.teleport(new Location(world, x, y, z, (float) yaw, (float) pitch));
    }

    @EventHandler
    public void onPlayerJoinMOTD(PlayerJoinEvent e){
        if(!plugin.getConfig().getBoolean("enable-motd")) return;
        Player player = e.getPlayer();

        for(String s : WorldConfig.get().getStringList("world.motd")){
            player.sendMessage(Colorize.Color(s));
        }
    }

}
