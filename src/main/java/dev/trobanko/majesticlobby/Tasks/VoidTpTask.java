package dev.trobanko.majesticlobby.Tasks;

import dev.trobanko.majesticlobby.MajesticLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class VoidTpTask extends BukkitRunnable {

    MajesticLobby plugin;

    public VoidTpTask(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        if(!plugin.getConfig().getBoolean("void-tp-to-spawn")) {
            return;
        }

        String worldName = plugin.getConfig().getString("lobby-world");

        try{
            for(Player p : Bukkit.getOnlinePlayers()){
                if(p.getLocation().getY() < 1){
                    p.teleport(Bukkit.getWorld(worldName).getSpawnLocation());
                }
            }
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "An error occurred while teleporting player to spawn. Maybe spawn isn't set?");
        }
    }

}
