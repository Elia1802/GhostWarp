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

package de.elia.ghostwarp.commands.del;

import de.elia.ghostmain.all.plugins.prefix.Prefix;
import de.elia.ghostwarp.GhostWarp;
import de.elia.ghostwarp.plugin.config.GhostWarpConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DelWarpCommand implements CommandExecutor{

    private final GhostWarp plugin;

    private Player player;

    private String locationName;

    public DelWarpCommand(GhostWarp plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().warning(Prefix.getGhostLogger() + "You have to be a Player!");
            return false;
        }

        player = (Player) sender;
        if (!player.hasPermission("ghost.owner")){
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "You have don't permission for this Command!");
        }
        if (!player.hasPermission("ghost.admin")){
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "You have don't permission for this Command!");
        }
        if (!player.hasPermission("ghost.developer")){
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "You have don't permission for this Command!");
        }
        if (args.length == 0) {
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "/delwarp [NAME]");
        }
        locationName = args[0].toLowerCase();

        if (GhostWarpConfig.get().get(locationName) == null) {
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "This Warp is not exist!");
        }else {
            GhostWarpConfig.get().set(locationName , null);
            GhostWarpConfig.save();
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.AQUA + "The Warp " + ChatColor.GREEN + locationName + ChatColor.AQUA + " is deleted!");
        }
        return true;
    }
}
