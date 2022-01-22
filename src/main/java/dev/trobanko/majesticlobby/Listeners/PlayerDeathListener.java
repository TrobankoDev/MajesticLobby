package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.MajesticLobby;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {
    MajesticLobby plugin;

    public PlayerDeathListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        if(!plugin.getConfig().getBoolean("disable-death-messages") && !plugin.getConfig().getBoolean("clear-drops-on-death") ) return;
        if(plugin.getConfig().getBoolean("disable-death-messages")) {
            e.setDeathMessage(null);
        }
        if(plugin.getConfig().getBoolean("clear-drops-on-death")){
            e.getDrops().clear();
        }else{
            e.getEntity().getInventory().setContents(e.getDrops().toArray(new ItemStack[0]));
        }

    }

}
