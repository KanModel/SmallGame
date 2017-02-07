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
            Player player = event.getEntity();
//            Bukkit.getConsoleSender().sendMessage(Color.RED + event.getDeathMessage() + " " + event.getEntity().getGameMode().name());
            event.setDeathMessage(null);
//            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"gamemode 3 " + player.getName());
            player.setGameMode(GameMode.SPECTATOR);
            GeneralPluginProvider.getGamers().getGamer(player).setChannel(Channel.getJudgeC(), Gamer.GAMER_JUDGE);
            l = player.getLocation();
            l.getBlock().setType(Material.DIAMOND_BLOCK);
//            Bukkit.getServer().getWorld(player.getWorld().getUID())
            Bukkit.getConsoleSender().sendMessage("X:" + l.getBlockX() + " Y:" + l.getBlockY() + " Z:" + l.getBlockZ());
        }
    }

//    @EventHandler
//    public void onPlayerAlive(PlayerSpawnLocationEvent event){
//        if (Lobby.list.contains(event.getPlayer())){
//            Bukkit.getConsoleSender().sendMessage("12312345674489");
//            event.setSpawnLocation(location);
//        }
//    }


    public static Location getLocation() {
        return l;
    }
}
