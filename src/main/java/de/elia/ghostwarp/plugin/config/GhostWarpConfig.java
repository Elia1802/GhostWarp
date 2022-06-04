package de.elia.ghostwarp.plugin.config;

import de.elia.ghostmain.all.plugins.prefix.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GhostWarpConfig {

    private static File file;

    //Create Variable fileConfiguration
    private static FileConfiguration fileConfiguration;

    // .setup() Methode
    public static void setup(){
        //Give File a new value
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("GhostWarp").getDataFolder() , "Warps.yml");

        //Checks if the file exists
        if (!file.exists()) {
            try {
                //Create the file
                file.createNewFile();
            }catch (IOException exception) {
                //If what goes wrong when creating the file
                Bukkit.getLogger().severe(Prefix.getGhostLogger() + "Failed to create Warps.yml!");
                Bukkit.getLogger().severe(Prefix.getGhostLogger() + exception.getMessage());
                Bukkit.getLogger().severe(Prefix.getGhostLogger() + "An error occurred while creating the Config Warps.yml");
                Bukkit.getLogger().severe(Prefix.getGhostLogger() + "The error is at de.elia.ghostmain.plugin.config.PluginConfig");
            }
        }

        //If the file exists, it will be loaded
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    //Create .get() to add or change something in the config later
    public static FileConfiguration get(){
        return fileConfiguration;
    }

    public static void save(){
        try {
            //save the File
            fileConfiguration.save(file);
        }catch (IOException exception) {
            //If something goes wrong when saving, report it to the console
            Bukkit.getLogger().severe(Prefix.getGhostLogger() + "Couldn't save the Warps.yml: ");
            Bukkit.getLogger().severe(Prefix.getGhostLogger() + exception.getMessage());
            Bukkit.getLogger().severe(Prefix.getGhostLogger() + "An error occurred while saving the Config GhostMain.yml");
            Bukkit.getLogger().severe(Prefix.getGhostLogger() + "Der Fehler befindet sich bei de.elia.ghostmain.plugin.config.PluginConfig");
        }
    }

    //Create .reload() to reload the file
    public static void reload(){
        //If the file exists, it will be loaded
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

}
