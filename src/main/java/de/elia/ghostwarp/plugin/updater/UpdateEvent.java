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


package de.elia.ghostwarp.plugin.updater;

import de.elia.ghostmain.all.plugins.prefix.Prefix;
import de.elia.ghostmain.all.plugins.updater.Updater;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateEvent implements Listener {

    private JavaPlugin plugin;

    @EventHandler
    public void onUpdate(PlayerJoinEvent event){
        new Updater(plugin , 102443).getVersion(version -> {
            if (plugin.getDescription().getVersion().equals(version)) {
                event.getPlayer().sendMessage("");
            }else if (event.getPlayer().hasPermission("ghost.owner")) {
                event.getPlayer().sendMessage(Prefix.getGhostWarpPrefix() + "A new Update for the Ghostwarp System is available!");
            }else if (event.getPlayer().hasPermission("ghost.developer")){
                event.getPlayer().sendMessage(Prefix.getGhostWarpPrefix() + "A new Update for the Ghostwarp System is available!");
            }
        });
    }

}
