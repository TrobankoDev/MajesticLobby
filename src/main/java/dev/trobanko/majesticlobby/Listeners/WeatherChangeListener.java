package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.MajesticLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.logging.Level;

public class WeatherChangeListener implements Listener {

    MajesticLobby plugin;

    public WeatherChangeListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        if(!plugin.getConfig().getBoolean("always-clear-weather")){
            return;
        }
            e.setCancelled(true);
            plugin.getLogger().log(Level.FINEST, "Successfully changed weather back to clear...");
    }


}
