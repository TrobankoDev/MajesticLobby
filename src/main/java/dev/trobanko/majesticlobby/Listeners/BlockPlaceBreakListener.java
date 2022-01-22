package dev.trobanko.majesticlobby.Listeners;

import dev.trobanko.majesticlobby.MajesticLobby;
import dev.trobanko.majesticlobby.Utils.BannedInteracts;
import dev.trobanko.majesticlobby.Utils.Colorize;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockPlaceBreakListener implements Listener {

    MajesticLobby plugin;

    public BlockPlaceBreakListener(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockPlaceEvent e){
        if(e.getPlayer().hasPermission("majesticlobby.bypass") || e.getPlayer().hasPermission("majesticlobby.admin")){
            return;
        }
        Player placer = e.getPlayer();
        if(!plugin.getConfig().getBoolean("disable-block-place")){
            return;
        }
        e.setCancelled(true);
        placer.sendMessage(Colorize.Color("&cYou cannot place blocks here!"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(e.getPlayer().hasPermission("majesticlobby.bypass") || e.getPlayer().hasPermission("majesticlobby.admin")){
            return;
        }
        Player placer = e.getPlayer();
        if(!plugin.getConfig().getBoolean("disable-block-break")){
            return;
        }
        e.setCancelled(true);
        placer.sendMessage(Colorize.Color("&cYou cannot break blocks here!"));
    }

    @EventHandler
    public void onBlockBurn(BlockIgniteEvent e){
        if(e.getPlayer().hasPermission("majesticlobby.bypass") || e.getPlayer().hasPermission("majesticlobby.admin")){
            return;
        }
        Player placer = e.getPlayer();
        if(!plugin.getConfig().getBoolean("disable-block-place")){
            return;
        }
        e.setCancelled(true);
        placer.sendMessage(Colorize.Color("&cYou cannot set blocks on fire here!"));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if(e.getPlayer().hasPermission("majesticlobby.bypass") || e.getPlayer().hasPermission("majesticlobby.admin")){
            return;
        }
        if(!plugin.getConfig().getBoolean("disable-item-interaction")){
            return;
        }
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Material clicked = e.getClickedBlock().getType();
        if(BannedInteracts.bannedInteracts.contains(clicked)){
            e.setCancelled(true);
            e.getPlayer().sendMessage(Colorize.Color("&cYou cannot interact with that!"));
        }
    }

}
