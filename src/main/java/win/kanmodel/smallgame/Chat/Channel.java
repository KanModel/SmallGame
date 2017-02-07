package win.kanmodel.smallgame.Chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



public class Channel {
	private static final Channel normalC = new Channel("Normal");
	private static final Channel killerC = new Channel("Killer");
	private static final Channel judgeC = new Channel("Judge");
	//	private static GeneralPluginProvider instance = new GeneralPluginProvider();
//	List<Customer> listCustomer = new List<Customer>() { new Customer(), new Customer(), new Customer() };
//	private static final ArrayList<Channel> allC = new ArrayList<>();
	private String Name = null;
	public List<Player> players = new ArrayList<>();
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
	
	public void broadcastMessage(String message, Player player){
		players.forEach(p->{
			p.sendMessage(ChatColor.GOLD + "[" + this.Name + " - " + player.getName() + "]" + ChatColor.WHITE + message);
		});
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
}
