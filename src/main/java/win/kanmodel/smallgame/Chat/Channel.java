package win.kanmodel.smallgame.Chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import win.kanmodel.smallgame.Gamer.Gamer;
import win.kanmodel.smallgame.GeneralPluginProvider;


public class Channel {
	private static final Channel normalC = new Channel("Normal");
	private static final Channel killerC = new Channel("Killer");
	private static final Channel judgeC = new Channel("Judge");
	private static final Channel deadC = new Channel("dead");
	//	private static GeneralPluginProvider instance = new GeneralPluginProvider();
//	List<Customer> listCustomer = new List<Customer>() { new Customer(), new Customer(), new Customer() };
//	private static final ArrayList<Channel> allC = new ArrayList<>();
	private String Name = null;
	private List<Player> players = new ArrayList<>();
	public Channel(String name){
		this.Name = name;
	}
	
	public void addPlayer(Player player){
		this.players.add(player);
	}
	
	public boolean removePlayer(Player player){
		if(!this.players.contains(player))
			return false;
		this.players.remove(this.players.indexOf(player));
		return true;
	}
	
	public void setName(String name){
		this.Name = name;
	}
	
	public String getName(){
		return this.Name;
	}
	
	public void broadcastMessage(String message, Player player) {
		if (GeneralPluginProvider.getGamers().getGamer(player).getType() == Gamer.GAMER_JUDGE) {
			GeneralPluginProvider.getGamers().getGamer(player).getCurrentChannel().getPlayers().forEach(p -> {
				p.sendMessage(ChatColor.RED + "[" + this.Name + " - " + player.getName() + "]" + ChatColor.WHITE + message);
			});
		}else if (GeneralPluginProvider.getGamers().getGamer(player).getType() == Gamer.GAMER_NORMAL){
//			this.players.forEach(p -> {
//				p.sendMessage(ChatColor.GOLD + "[" + this.Name + " - " + player.getName() + "]" + ChatColor.WHITE + message);
//			});
			getNormalC().getPlayers().forEach(p -> {
				p.sendMessage(ChatColor.GOLD + "[" + getNormalC().getName() + " - " + player.getName() + "]" + ChatColor.WHITE + message);
			});

			getJudgeC().getPlayers().forEach(p -> {
				p.sendMessage(ChatColor.GOLD + "[" + getNormalC().getName() + " - " + player.getName() + "]" + ChatColor.WHITE + message);
			});
		}else if (GeneralPluginProvider.getGamers().getGamer(player).getType() == Gamer.GAMER_KILLER){
//			this.players.forEach(p -> {
//				p.sendMessage(ChatColor.GOLD + "[" + this.Name + " - " + player.getName() + "]" + ChatColor.WHITE + message);
//			});
			getKillerC().getPlayers().forEach(p -> {
				p.sendMessage(ChatColor.GOLD + "[" + getKillerC().getName() + " - " + player.getName() + "]" + ChatColor.WHITE + message);
			});

			getJudgeC().getPlayers().forEach(p -> {
				p.sendMessage(ChatColor.GOLD + "[" + getKillerC().getName() + " - " + player.getName() + "]" + ChatColor.WHITE + message);
			});
		}
		//judge可以看到所有频道的话
//		getJudgeC().getPlayers().forEach(p->{
//			p.sendMessage(ChatColor.GOLD + "[" + this.Name + " - " + player.getName() + "]" + ChatColor.WHITE + message);
//		});
	}

	public void broadcastMessage(String message){
		players.forEach(p->{
			p.sendMessage(ChatColor.GOLD + "[" + this.Name + " - " + p.getName() + "]" + ChatColor.WHITE + message);
		});
	}

	public static Channel getNormalC() {
		return normalC;
	}

	public static Channel getKillerC() {
		return killerC;
	}

	public static Channel getJudgeC() {
		return judgeC;
	}
	
	public static Channel getDeadC() {
		return deadC;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
