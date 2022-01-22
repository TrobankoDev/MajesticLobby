package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.MajesticLobby;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    MajesticLobby plugin;

    public EntitySpawnListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e){
        if(e.getEntity() instanceof Player) return;
        if(!plugin.getConfig().getBoolean("disable-mob-spawning") && !plugin.getConfig().getBoolean("disable-animal-spawning")) return;
        if(plugin.getConfig().getBoolean("disable-mob-spawning") && (e.getEntity() instanceof Monster || e.getEntity() instanceof Slime))
            e.setCancelled(true);
        if(plugin.getConfig().getBoolean("disable-animal-spawning") && !(e.getEntity() instanceof Monster))
            e.setCancelled(true);
    }
}
