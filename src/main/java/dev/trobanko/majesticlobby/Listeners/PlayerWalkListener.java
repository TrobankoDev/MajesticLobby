package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.ConfigHandler.WorldConfig;
import dev.trobanko.majesticlobby.MajesticLobby;
import dev.trobanko.majesticlobby.Utils.BeingLaunched;
import dev.trobanko.majesticlobby.Utils.Colorize;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.logging.Level;

public class PlayerWalkListener implements Listener {

    MajesticLobby plugin;

    public PlayerWalkListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent e){
        if(!plugin.getConfig().getBoolean("enable-slime-pads")) return;
        Location loc = e.getPlayer().getLocation();
        loc.setY(loc.getY()-1);

        String worldName = plugin.getConfig().getString("lobby-world");

        try{
            Bukkit.getServer().getWorld(worldName).setTime(4284L);
        } catch (NullPointerException ex) {
            plugin.getLogger().log(Level.WARNING, "The world " + worldName + " is invalid! Please update the config.yml");
        }

        if(Bukkit.getWorld(worldName).getBlockAt(loc).getType().equals(Material.SLIME_BLOCK)){
            Player player = e.getPlayer();
            player.setVelocity(player.getLocation().getDirection().multiply(5).setY(2));
            player.sendMessage(Colorize.Color(WorldConfig.get().getString("world.launchpad-message")));
            BeingLaunched.addPlayer(player);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME,1,1);
        }

    }
}
