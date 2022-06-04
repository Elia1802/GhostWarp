/**
 * @author Elia
 * @plugin GhostMain
 * @Class Prefix.java
 *
 * @license
 * Copyright (c) 2022 Elia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */


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
