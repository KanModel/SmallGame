package win.kanmodel.smallgame.Gamer;

import java.util.List;

import org.bukkit.entity.Player;

import win.kanmodel.smallgame.Chat.Channel;

public class Gamers {
	public List<Gamer> gamers;
	public Gamer newGamer(Player player,int type,Channel currentChannel,boolean isDead){
		Gamer gamer = new Gamer(player,type,currentChannel,isDead);
		this.gamers.add(gamer);
		return gamer;
	}
	
	public void removeGamer(Gamer gamer){
		if(!this.gamers.contains(gamer))
			return;
		this.gamers.remove(gamer);
	}
	
	public Gamer getGamer(Player player){
		return gamers.stream().filter(g->g.getPlayer().equals(player)).findAny().orElse(null);
	}
	
	
}
