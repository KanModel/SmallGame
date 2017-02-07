package win.kanmodel.smallgame.Listener;

import me.confuser.barapi.BarAPI;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;
import win.kanmodel.smallgame.Chat.Channel;
import win.kanmodel.smallgame.Gamer.Gamer;
import win.kanmodel.smallgame.Gamer.Gamers;
import win.kanmodel.smallgame.GeneralPluginProvider;
import win.kanmodel.smallgame.Lobby;

import java.sql.SQLException;

import static win.kanmodel.smallgame.Lobby.refresh;

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
                gamer.setType(Gamer.GAMER_JUDGE);
            	event.setDeathMessage(null);
            	gamer.kill();
            	l = gamer.getPlayer().getLocation();
                l.getBlock().setType(Material.DIAMOND_BLOCK);
                Bukkit.getConsoleSender().sendMessage("X:" + l.getBlockX() + " Y:" + l.getBlockY() + " Z:" + l.getBlockZ());
                if (GeneralPluginProvider.getGamers().getStatus() == Gamers.KILL){
//                    event.getPlayer().sendMessage("你找到了尸体！");
                    GeneralPluginProvider.getGamers().setStatus(Gamers.SEARCH);
                    refresh("有人死亡，全员开始调查！");
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
                                        refresh("投票进行中...结束还有: " + time + "s 人数:[" + Lobby.getList().size() + "]");
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

    public static Location getLocation() {
        return l;
    }

    public static void setL(Location l) {
        death.l = l;
    }
}
