package win.kanmodel.smallgame;

import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by kgdwhsk on 2017/2/6.
 */
public class Lobby {
    public static final ArrayList<Player> list = new ArrayList();

    public static void join(Player player){
        list.add(player);
        BarAPI.setMessage(player,"欢迎加入[弹丸论破] 等待游戏开始中...人数:[" + list.size() + "]");
        refresh();
    }

    public static void quit(Player player){
        BarAPI.removeBar(player);
        list.remove(player);
        refresh();
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"gamemode 0 " + player.getName());
    }

    public static void refresh(){
        for (int i = 0; i < list.size(); i++){
            if(list.size() > 0){
                Player player = list.get(i);
                BarAPI.setMessage(player,"欢迎加入[弹丸论破] 等待游戏开始中...人数:[" + list.size() + "]");

            }
        }
    }

    public static ArrayList<Player> getList() {
        return list;
    }
}
