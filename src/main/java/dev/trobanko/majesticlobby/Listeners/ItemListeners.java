package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.MajesticLobby;
import dev.trobanko.majesticlobby.Utils.Colorize;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

import java.util.logging.Level;

public class ItemListeners implements Listener {

    MajesticLobby plugin;

    public ItemListeners(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        if(e.getPlayer().hasPermission("majesticlobby.bypass") || e.getPlayer().hasPermission("majesticlobby.admin")) return;
        if(!plugin.getConfig().getBoolean("disable-drop-item")) return;
        e.setCancelled(true);
        e.getPlayer().sendMessage(Colorize.Color("&cYou are not allowed to drop items here!"));
    }

    @EventHandler
    public void onItemMove(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(player.hasPermission("majesticlobby.bypass") || player.hasPermission("majesticlobby.admin")) return;
        if(!plugin.getConfig().getBoolean("disable-item-move-in-inventory")) return;
        e.setCancelled(true);

    }
    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent e){
        if(!plugin.getConfig().getBoolean("disable-item-durability")) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if(player.hasPermission("majesticlobby.bypass") || player.hasPermission("majesticlobby.admin")) return;
        if(!plugin.getConfig().getBoolean("disable-pickup-items")) return;
        e.setCancelled(true);

    }

}
