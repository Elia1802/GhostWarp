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

package de.elia.ghostwarp;

import de.elia.ghostmain.all.plugins.prefix.Prefix;
import de.elia.ghostmain.all.plugins.updater.Updater;
import de.elia.ghostmain.GhostMain;
import de.elia.ghostwarp.commands.del.DelWarpCommand;
import de.elia.ghostwarp.commands.set.SetWarpCommand;
import de.elia.ghostwarp.commands.teleport.WarpCommand;
import de.elia.ghostwarp.plugin.config.GhostWarpConfig;
import de.elia.ghostwarp.plugin.prefix.ErrorPrefix;
import de.elia.ghostwarp.plugin.updater.UpdateEvent;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GhostWarp extends JavaPlugin {

    private static final GhostMain ghostMain = (GhostMain) Bukkit.getPluginManager().getPlugin("GhostMain");

    private final GhostWarpConfig config = new GhostWarpConfig(this , "Locations.yml");

    private static GhostWarp instance;

    @Override
    public void onEnable() {
        if (ghostMain != null) {
            instance = this;
            Bukkit.getLogger().info(Prefix.getGhostLogger() + "Start GhostWarp");
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "load Commands");
                    Bukkit.getPluginCommand("setwarp").setExecutor(new SetWarpCommand(this));
                    Bukkit.getPluginCommand("warp").setExecutor(new WarpCommand(this));
                    Bukkit.getPluginCommand("delwarp").setExecutor(new DelWarpCommand(this));
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "Commands loaded");
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "load Events");
                    Bukkit.getPluginManager().registerEvents(new  UpdateEvent() , this);
                Bukkit.getLogger().info(Prefix.getGhostLogger() + "Events loaded");
            Bukkit.getLogger().info(Prefix.getGhostLogger() + "load Config");
                GhostWarp.getInstance().getConfiguration().copyDefaults(true);
                GhostWarp.getInstance().getConfiguration().save();
            Bukkit.getLogger().info(Prefix.getGhostLogger() + "Config loaded");
            Bukkit.getLogger().info(Prefix.getGhostLogger() + "checks for Updates");
                new Updater(this , 102443).getVersion(version -> {
                    if (!this.getDescription().getVersion().equals(version)) {
                        Bukkit.getLogger().warning(Prefix.getGhostLogger() + "There is a new Update for the GhostWarp System available!");
                    }else {
                        Bukkit.getLogger().info(Prefix.getGhostLogger() + "There is not a new Update for the GhostWarp System available!");
                    }
                });
            Bukkit.getLogger().info(Prefix.getGhostLogger() + "Update checks");
            Bukkit.getLogger().info(Prefix.getGhostLogger() + "GhostWarp started");
        }else {
            Bukkit.getLogger().severe(ErrorPrefix.getErrorPrefix() + "GhostMain plugin is missing!");
            Bukkit.getLogger().severe(ErrorPrefix.getErrorPrefix() + "The plugin will not work without this plugin!");
            Bukkit.getLogger().severe(ErrorPrefix.getErrorPrefix() + "This plugin is therefore deactivated!");
            Bukkit.getLogger().severe(ErrorPrefix.getErrorPrefix() + "Download GhostMain: https://www.spigotmc.org/resources/ghost-main.102115/");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().warning("Stop GhostWarp System!");
        Bukkit.getLogger().info("save Config!");
        Bukkit.getLogger().info("Config saved!");
        Bukkit.getLogger().info("GhostWarp-System stopped!");
    }

    public static GhostWarp getInstance() {
        return instance;
    }

    public GhostWarpConfig getConfiguration() {
        return config;
    }
}
