package dev.trobanko.majesticlobby.Tasks;

import dev.trobanko.majesticlobby.MajesticLobby;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class AlwaysDayTask extends BukkitRunnable {

    MajesticLobby plugin;

    public AlwaysDayTask(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        if(!plugin.getConfig().getBoolean("always-day")) {
            return;
        }

        String worldName = plugin.getConfig().getString("lobby-world");

        try{
            Bukkit.getServer().getWorld(worldName).setTime(4284L);
        } catch (NullPointerException e) {
            plugin.getLogger().log(Level.WARNING, "The world " + worldName + " is invalid! Please update the config.yml");
        }
    }
}
