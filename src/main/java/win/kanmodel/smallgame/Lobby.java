package win.kanmodel.smallgame;

import me.confuser.barapi.BarAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import win.kanmodel.smallgame.Chat.Channel;
import win.kanmodel.smallgame.Gamer.Gamer;
import win.kanmodel.smallgame.Gamer.Gamers;

import java.util.ArrayList;

/**
 * Created by kgdwhsk on 2017/2/6.
 */
public class Lobby {
    public static final ArrayList<Player> list = new ArrayList();

    public static void join(Player player){
        if (Gamers.getStatus() == Gamers.BEGAIN) {
            if (list.contains(player)) {
                player.sendMessage("你已经加入！！！");
            } else {
                list.add(player);
//        Bukkit.getConsoleSender().sendMessage(Channel.getAllC().size() + "");
                GeneralPluginProvider.getGamers().newGamer(player, 0, Channel.getNormalC(), false);
//        Channel.getAllC().get(Gamer.GAMER_NORMAL + 1).addPlayer(player);
                Channel.getNormalC().addPlayer(player);
                BarAPI.setMessage(player, "欢迎加入[弹丸论破] 等待游戏开始中...人数:[" + list.size() + "]");
                refresh();
                if (list.size() >= 2){
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
                            if (list.size() < 2){
                                cancel();
                            }
                            refresh("[弹丸论破]游戏开始倒计时: " + time + "s 人数:[" + list.size() + "]");
                            if(time == 0) {
//                                coolDownNow = false;
                                refresh("[弹丸论破]游戏开始!");
                                Gamers.setStatus(Gamers.KILL);
                                cancel();  // 终止线程
                                return;
                            }
                        }
                    }.runTaskTimer(GeneralPluginProvider.getInstance().getPlugin(), 0L, 20L);
                }
            }
        }else {
            player.sendMessage("游戏正在进行中！");
        }
    }

    public static void quit(Player player){
        if (Gamers.getStatus() == Gamers.BEGAIN){
            BarAPI.removeBar(player);
            Channel.getJudgeC().removePlayer(player);
            Channel.getKillerC().removePlayer(player);
            Channel.getNormalC().removePlayer(player);
            list.remove(player);
            refresh();
//        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"gamemode 0 " + player.getName());
            player.setGameMode(GameMode.SURVIVAL);
        }else {
            player.sendMessage("游戏正在进行中！");
        }
    }

    public static void refresh(){
//        for (int i = 0; i < list.size(); i++){
//            if(list.size() > 0){
//                Player player = list.get(i);
//                BarAPI.setMessage(player,"欢迎加入[弹丸论破] 等待游戏开始中...人数:[" + list.size() + "]");
//            }
//        }
        if(Gamers.getStatus() == Gamers.BEGAIN){
            list.forEach(player -> BarAPI.setMessage(player,"欢迎加入[弹丸论破] 等待游戏开始中...人数:[" + list.size() + "]"));
        }else if (Gamers.getStatus() == Gamers.KILL){
            list.forEach(player -> BarAPI.setMessage(player,"游戏开始！"));
        }else if (Gamers.getStatus() == Gamers.SEARCH){
//            list.forEach(player -> BarAPI.setMessage(player,"发现尸体，开始学级裁判！"));
            list.forEach(player -> BarAPI.setMessage(player,"发现尸体，全员开始调查！"));
        }
    }

    public static void refresh(String msg){
        list.forEach(player -> BarAPI.setMessage(player,msg));
    }

    public static void refresh(String msg, Player p){
        list.forEach(player -> BarAPI.setMessage(player,"欢迎加入[弹丸论破] 等待游戏开始中...人数:[" + list.size() + "]"));
    }

    public static void removePlayer(Player player){
        BarAPI.removeBar(player);
        GeneralPluginProvider.getGamers().getGamer(player).getCurrentChannel().removePlayer(player);
        list.remove(player);
        refresh();
        Channel.getJudgeC().removePlayer(player);
        Channel.getKillerC().removePlayer(player);
        Channel.getNormalC().removePlayer(player);
    }

    public static ArrayList<Player> getList() {
        return list;
    }
}
