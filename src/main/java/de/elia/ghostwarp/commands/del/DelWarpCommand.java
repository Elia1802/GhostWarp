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
import de.elia.ghostmain.GhostMain;
import de.elia.ghostwarp.GhostWarp;
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

    private final String ownerPermissionID = "ghostowner";

    private final String adminPermissionID = "ghostadmin";

    private final String developerPermissionID = "ghostdeveloper";

    private final String moderatorPermissionID = "ghostmoderator";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().warning(Prefix.getGhostLogger() + "You have to be a Player!");
            return false;
        }

        player = (Player) sender;
        if (GhostMain.getInstance().getPermissionOwnerConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + ownerPermissionID , true)){
            locationName = args[0].toLowerCase();

            if (GhostWarp.getInstance().getConfiguration().get(locationName) == null) {
                player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "This Warp is not exist!");
            }else {
                GhostWarp.getInstance().getConfiguration().set(locationName , null);
                GhostWarp.getInstance().getConfiguration().save();
                player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.AQUA + "The Warp " + ChatColor.GREEN + locationName + ChatColor.AQUA + " is deleted!");
            }
        }else if (GhostMain.getInstance().getPermissionAdminConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + adminPermissionID ,true)){
            locationName = args[0].toLowerCase();

            if (GhostWarp.getInstance().getConfiguration().get(locationName) == null) {
                player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "This Warp is not exist!");
            }else {
                GhostWarp.getInstance().getConfiguration().set(locationName , null);
                GhostWarp.getInstance().getConfiguration().save();
                player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.AQUA + "The Warp " + ChatColor.GREEN + locationName + ChatColor.AQUA + " is deleted!");
            }
        }else if (GhostMain.getInstance().getPermissionDeveloperConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + developerPermissionID ,true)){
            locationName = args[0].toLowerCase();

            if (GhostWarp.getInstance().getConfiguration().get(locationName) == null) {
                player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "This Warp is not exist!");
            }else {
                GhostWarp.getInstance().getConfiguration().set(locationName , null);
                GhostWarp.getInstance().getConfiguration().save();
                player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.AQUA + "The Warp " + ChatColor.GREEN + locationName + ChatColor.AQUA + " is deleted!");
            }
        }else if (GhostMain.getInstance().getPermissionModeratorConfiguration().get(".Name " + player.getName() + " " + ".UniqueID " + player.getUniqueId() + " " + ".Permission " + moderatorPermissionID ,true)) {
            player.sendMessage(Prefix.getGhostWarpPrefix() + ChatColor.RED + "You have don't permission for this Command!");
        }
        return true;
    }
}
