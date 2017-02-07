package win.kanmodel.smallgame.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import win.kanmodel.smallgame.Lobby;

import java.sql.SQLException;

/**
 * Created by kgdwhsk on 2017/2/6.
 */
public class death  implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        if (Lobby.list.contains(player)){
//            player.sendMessage("gg");
//            player.performCommand("gamemode 3");
//            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"gamemode 3 " + player.getName());
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
