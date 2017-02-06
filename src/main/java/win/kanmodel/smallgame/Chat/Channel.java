package win.kanmodel.smallgame.Chat;

import java.util.List;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;


public class Channel {
	private String Name = null;
	public List<Player> players;
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
	
	public void broadcastMessage(String message){
		players.forEach(p->{
			p.sendMessage(ChatColor.GOLD + "[" + this.Name + "]" + ChatColor.WHITE + message);
		});
	}
}
