package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.MajesticLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerChangeListener implements Listener {

    MajesticLobby plugin;

    public HungerChangeListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent e){
        if(!plugin.getConfig().getBoolean("disable-hunger")){
            return;
        }
        e.setCancelled(true);
    }

}
