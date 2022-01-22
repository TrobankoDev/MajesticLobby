package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.MajesticLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {

    MajesticLobby plugin;

    public ExplosionListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e){
        if(!plugin.getConfig().getBoolean("disable-explosions")) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        if(!plugin.getConfig().getBoolean("disable-explosions")) return;
        e.setCancelled(true);
    }
}
