package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.MajesticLobby;
import dev.trobanko.majesticlobby.Utils.BeingLaunched;
import dev.trobanko.majesticlobby.Utils.Colorize;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    MajesticLobby plugin;

    public PlayerDamageListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof EntityDamageByEntityEvent){
            return;
        }
        if(!plugin.getConfig().getBoolean("disable-damage")){
            return;
        }
        e.setCancelled(true);

    }

    @EventHandler
    public void onPlayerDamageByPlayer(EntityDamageByEntityEvent e){
        if(!plugin.getConfig().getBoolean("disable-damage")){
            return;
        }


        if (e.getDamager() instanceof Player){
            Player damager = (Player) e.getDamager();
            if(damager.hasPermission("majesticlobby.bypass") || damager.hasPermission("majesticlobby.admin")){
                return;
            }
            damager.sendMessage(Colorize.Color("&cPvp is disabled in this area!"));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDamageByBeingLaunched(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL) && BeingLaunched.isLaunched(player)){
            e.setCancelled(true);
            BeingLaunched.removePlayer(player);
        }
    }

}
