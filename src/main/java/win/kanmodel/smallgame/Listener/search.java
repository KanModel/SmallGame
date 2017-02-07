package win.kanmodel.smallgame.Listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import win.kanmodel.smallgame.Gamer.Gamer;
import win.kanmodel.smallgame.Gamer.Gamers;
import win.kanmodel.smallgame.GeneralPluginProvider;
import win.kanmodel.smallgame.Lobby;

/**
 * Created by kgdwhsk on 2017/2/7.
 */
public class search implements Listener {
    double lx,ly,lz;
    @EventHandler
    public void onSearch(PlayerMoveEvent event){
        if (Lobby.getList().contains(event.getPlayer()) && GeneralPluginProvider.getGamers().getGamer(event.getPlayer()).getType() == Gamer.GAMER_NORMAL){
            if (death.getLocation() != null){
                lx = death.getLocation().getBlockX();
                ly = death.getLocation().getBlockY();
                lz = death.getLocation().getBlockZ();
            }
            if (distanceCalc(event.getPlayer(), event.getTo())){
                if (GeneralPluginProvider.getGamers().getStatus() == Gamers.KILL){
                    event.getPlayer().sendMessage("你找到了尸体！");
                    GeneralPluginProvider.getGamers().setStatus(Gamers.SEARCH);
                    Lobby.refresh("发现尸体，全员开始调查！");
                }
            }
        }
    }

    public boolean distanceCalc(Player player, Location l){
        double dis = Math.sqrt(((lx - l.getBlockX()) * (lx - l.getBlockX())) + ((ly - l.getBlockY()) * (ly - l.getBlockY())) + ((lz - l.getBlockZ()) * (lz - l.getBlockZ())));
//        player.sendMessage(dis + " dis" + "X:" + l.getBlockX() + " Y:" + l.getBlockY() + " Z:" + l.getBlockZ());
        if (dis < 9){
            return true;
        }
        return false;
    }
}
