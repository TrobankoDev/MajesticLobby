package dev.trobanko.majesticlobby.ConfigHandler;

import dev.trobanko.majesticlobby.MajesticLobby;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WorldConfig {

    private static File file;
    private static FileConfiguration WorldConfigFile;
    MajesticLobby plugin;

    public WorldConfig(MajesticLobby plugin) {
        this.plugin = plugin;
    }

    public static void setup(MajesticLobby plugin){
        file = new File(plugin.getDataFolder(), "world.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("A problem has occurred creating world.yml");
            }
        }
        WorldConfigFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return WorldConfigFile;
    }

    public static void save(){
        try{
            WorldConfigFile.save(file);

        }catch (IOException e){
            System.out.println("A problem occurred while saving world.yml");
        }
    }

    public static void reload(){
        WorldConfigFile = YamlConfiguration.loadConfiguration(file);
    }

}
