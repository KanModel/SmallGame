package win.kanmodel.smallgame.Listener;

import me.confuser.barapi.BarAPI;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import win.kanmodel.smallgame.Chat.Channel;
import win.kanmodel.smallgame.Gamer.Gamer;
import win.kanmodel.smallgame.Gamer.Gamers;
import win.kanmodel.smallgame.GeneralPluginProvider;
import win.kanmodel.smallgame.Lobby;

import static win.kanmodel.smallgame.Lobby.refresh;

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
                    refresh("发现尸体，全员开始调查！");
                    new BukkitRunnable() {
                        //                        int time = main.getMyConfig().getInt("ReportCD");  // 六十秒
                        int time = 10;
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                time--;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (Lobby.getList().size() < 2){
                                cancel();
                            }
                            refresh("距离学级裁判还有: " + time + "s 人数:[" + Lobby.getList().size() + "]");
                            if(time == 0) {
//                                coolDownNow = false;
                                refresh("开始投票");
                                Gamers.setStatus(Gamers.JUDGE);
                                new BukkitRunnable() {
                                    //                        int time = main.getMyConfig().getInt("ReportCD");  // 六十秒
                                    int time = 6;
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1000);
                                            time--;
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        if (Lobby.getList().size() < 2){
                                            cancel();
                                        }
                                        refresh("距离学级裁判结束还有: " + time + "s 人数:[" + Lobby.getList().size() + "]");
                                        if(time == 0) {
                                            Gamers.setStatus(Gamers.BEGAIN);
                                            Lobby.getList().forEach(player -> player.sendMessage("&3游戏结束胜利者为："));
                                            Lobby.getList().forEach(player -> BarAPI.removeBar(player));
                                            Lobby.getList().forEach(player -> player.setGameMode(GameMode.SURVIVAL));
                                            Lobby.getList().clear();
                                            Gamers.getGamers().clear();
                                            Channel.getKillerC().getPlayers().clear();
                                            Channel.getNormalC().getPlayers().clear();
                                            Channel.getJudgeC().getPlayers().clear();
                                            death.setL(null);
                                            cancel();  // 终止线程
                                            return;
                                        }
                                    }
                                }.runTaskTimer(GeneralPluginProvider.getInstance().getPlugin(), 0L, 20L);
                                cancel();  // 终止线程
                                return;
                            }
                        }
                    }.runTaskTimer(GeneralPluginProvider.getInstance().getPlugin(), 0L, 20L);
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
