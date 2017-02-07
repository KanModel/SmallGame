package win.kanmodel.smallgame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Server;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.confuser.barapi.BarAPI;

/**
 * Created by kgdwhsk on 2017/2/6.
 */
public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0 ||(args.length > 0 && args[0].equalsIgnoreCase("?"))) {
                //ReportWrong.ShowHelp(sender);
                showHelp(player);
                return true;
            }else if (args.length > 0 && args[0].equalsIgnoreCase("j")){
                //BarAPI.setMessage(player,"欢迎加入[弹丸论破] 等待游戏开始中...");
                Lobby.join(player);
//                Bukkit.createBossBar("fsadfds", BarColor.BLUE, BarStyle.SEGMENTED_6, BarFlag.CREATE_FOG);
                return true;
            }else if (args.length > 0 && args[0].equalsIgnoreCase("q")){
//                BarAPI.removeBar(player);
                Lobby.quit(player);
                return true;
            }else if (args.length > 0 && args[0].equalsIgnoreCase("t")){
//                BarAPI.removeBar(player);
                return true;
            }else if (args.length > 0 && args[0].equalsIgnoreCase("v")){
            	if(args[1] == null){
            		sender.sendMessage("请输入目标玩家名字");
            		return true;
            	}
            	GeneralPluginProvider.getVoteManager().vote(args[1]);
                return true;
            } else {
                showHelp(player);
                return true;
            }
        }else {
            showHelp(sender);
        }
        return false;
    }

    public void showHelp(CommandSender sender){
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "][----------" + GeneralPluginProvider.getNAME() + "-----------][");
        sender.sendMessage(ChatColor.GREEN + "/dgrp 或者 /dg 或者 /dp");
        sender.sendMessage(ChatColor.GREEN + "/dgrp j 加入游戏");
        sender.sendMessage(ChatColor.GREEN + "/dgrp q 退出游戏");
    }
}
