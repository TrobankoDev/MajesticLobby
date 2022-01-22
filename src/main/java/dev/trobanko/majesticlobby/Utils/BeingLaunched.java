package dev.trobanko.majesticlobby.Utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BeingLaunched {

    public static ArrayList<String> launched = new ArrayList<>();

    public static void addPlayer(Player player){
        launched.add(player.getUniqueId().toString());
    }
    public static boolean isLaunched(Player player){
        return launched.contains(player.getUniqueId().toString());
    }
    public static void removePlayer(Player player){
        if(isLaunched(player)){
            launched.remove(player.getUniqueId().toString());
        }
    }
}
