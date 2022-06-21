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

package de.elia.ghostwarp.commands.teleport;

import de.elia.ghostmain.all.plugins.prefix.Prefix;
import de.elia.ghostwarp.GhostWarp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WarpCommand implements CommandExecutor{

    private final GhostWarp plugin;

    private @Nullable String world;

    private Player player;

    private String locationName;

    public WarpCommand(GhostWarp plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().warning(Prefix.getGhostLogger() + "You have to be a Player!");
            return false;
        }

        player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.GRAY + "Please enter a " + ChatColor.AQUA + "name" + ChatColor.GRAY + "!");
            return false;
        }

        locationName = args[0].toLowerCase();

        if (GhostWarp.getInstance().getConfiguration().get(locationName) == null) {
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "This Warp not exist!");
        }else {
            world = String.valueOf(Bukkit.getServer().getWorld(""));
            world = GhostWarp.getInstance().getConfiguration().getString(locationName + ".World");
            double x = GhostWarp.getInstance().getConfiguration().getDouble(locationName + ".X");
            double y = GhostWarp.getInstance().getConfiguration().getDouble(locationName + ".Y");
            double z = GhostWarp.getInstance().getConfiguration().getDouble(locationName + ".Z");
            double yaw =  GhostWarp.getInstance().getConfiguration().getDouble(locationName + ".Pitch");
            double pitch = GhostWarp.getInstance().getConfiguration().getDouble(locationName + ".Yaw");
            Location location = new Location(Bukkit.getWorld(world), x , y , z , (float) pitch, (float) yaw);
            player.teleport(location);
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.GREEN + "You have been teleport to " + ChatColor.AQUA + locationName);
        }
        return false;
    }
}
