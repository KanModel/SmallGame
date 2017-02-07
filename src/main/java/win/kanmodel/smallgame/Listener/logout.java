package win.kanmodel.smallgame.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import win.kanmodel.smallgame.Lobby;

/**
 * Created by kgdwhsk on 2017/2/7.
 */
public class logout implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerQuitEvent event){
        if(Lobby.getList().contains(event.getPlayer())){
            Player player = event.getPlayer();
//            Bukkit.getConsoleSender().sendMessage(Color.GREEN + player.getName() + "quit");
            Lobby.removePlayer(player);
        }
    }
}
