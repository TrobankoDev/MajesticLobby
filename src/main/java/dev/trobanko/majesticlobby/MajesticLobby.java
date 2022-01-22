package dev.trobanko.majesticlobby;

import dev.trobanko.majesticlobby.Commands.ReloadConfigCommand;
import dev.trobanko.majesticlobby.Commands.SetSpawnCommand;
import dev.trobanko.majesticlobby.Commands.SpawnCommand;
import dev.trobanko.majesticlobby.ConfigHandler.WorldConfig;
import dev.trobanko.majesticlobby.Listeners.*;
import dev.trobanko.majesticlobby.Tasks.AlwaysDayTask;
import dev.trobanko.majesticlobby.Tasks.VoidTpTask;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class MajesticLobby extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Create and Save Config
        reloadConfig();

        // Creating some necessary config stuff


        // Register Events
        getServer().getPluginManager().registerEvents(new WeatherChangeListener(this), this);
        getServer().getPluginManager().registerEvents(new HungerChangeListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemListeners(this), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new EntitySpawnListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerWalkListener(this),this);

        // Set Command Executors
        getCommand("majesticreload").setExecutor(new ReloadConfigCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));

        // Run Tasks
        BukkitTask KeepDay = new AlwaysDayTask(this).runTaskTimer(this, 0,20L);
        BukkitTask VoidTpToSpawn = new VoidTpTask(this).runTaskTimer(this, 0,20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();

        saveDefaultConfig();
        FileConfiguration config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        WorldConfig.setup(this);
        WorldConfig.get().options().copyDefaults(true);

        if(!WorldConfig.get().contains("world.motd")) {
            String[] lines = {"&5=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=", "             &aWelcome to the server         ", "         &bthis is a default MOTD message   ", "        &cyou can change this in world.yml  ","&5=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="};
            WorldConfig.get().set("world.motd", lines);
        }
        if(!WorldConfig.get().contains("world.launchpad-message")){
            WorldConfig.get().set("world.launchpad-message", "&dWoooosh");
        }
        WorldConfig.save();
    }
}
