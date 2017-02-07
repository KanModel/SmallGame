package win.kanmodel.smallgame.Listener;

import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;
import win.kanmodel.smallgame.Chat.Channel;
import win.kanmodel.smallgame.Gamer.Gamer;
import win.kanmodel.smallgame.Gamer.Gamers;
import win.kanmodel.smallgame.GeneralPluginProvider;
import win.kanmodel.smallgame.Lobby;

import java.sql.SQLException;

/**
 * Created by kgdwhsk on 2017/2/6.
 */
public class death  implements Listener {
    private static Location l = null;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if (Lobby.list.contains(event.getEntity())){
            if(Gamers.getStatus() == Gamers.KILL){
            	Gamer gamer = GeneralPluginProvider.getGamers().getGamer(event.getEntity());
            	event.setDeathMessage(null);
            	gamer.kill();
            	l = gamer.getPlayer().getLocation();
                l.getBlock().setType(Material.DIAMOND_BLOCK);
                Bukkit.getConsoleSender().sendMessage("X:" + l.getBlockX() + " Y:" + l.getBlockY() + " Z:" + l.getBlockZ());
            }
        }
    }

    public static Location getLocation() {
        return l;
    }

    public static void setL(Location l) {
        death.l = l;
    }
}
