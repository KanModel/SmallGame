package win.kanmodel.smallgame.Vote;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import win.kanmodel.smallgame.GeneralPluginProvider;
import win.kanmodel.smallgame.Gamer.Gamer;

public class VoteManager {
	private Vote currentVote;
	public VoteManager(){
	}
	
	
	//新建投票
	//createVote(持续时间，玩家们)
	public Vote createVote(int life,List<Gamer> gamers){
		this.currentVote = new Vote(life,gamers);
		return this.currentVote;
	}
	
	
	//获取投票
	public Vote getVote(){
		return this.currentVote;
	}
	
	
	//取消投票
	public void removeVote(){
		this.currentVote.clear();
		this.currentVote = null;
	}
	
	
	//投票
	public void vote(String pName){
		this.currentVote.vote(GeneralPluginProvider.getGamers().getGamer(GeneralPluginProvider.getInstance().getPlugin().getServer().getPlayer(pName)));
	}
	
	
	//投票
	public void vote(Gamer gamer){
		this.currentVote.vote(gamer);
	}
	
	
	//获取结果(根据票数排列好的玩家们)
	public List<Gamer> getResult(){
		return this.currentVote.getResult();
	}
}
