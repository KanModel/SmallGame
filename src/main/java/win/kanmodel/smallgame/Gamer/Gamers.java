package win.kanmodel.smallgame.Gamer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import win.kanmodel.smallgame.Chat.Channel;

public class Gamers {
	public static List<Gamer> gamers = new ArrayList<>();
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

	public static List<Gamer> getGamers() {
		return gamers;
	}

}
