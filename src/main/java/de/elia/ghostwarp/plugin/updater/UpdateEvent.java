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
        new Updater(plugin , 0).getVersion(version -> {
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
