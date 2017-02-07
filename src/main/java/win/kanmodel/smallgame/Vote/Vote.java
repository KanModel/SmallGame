package win.kanmodel.smallgame.Vote;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import win.kanmodel.smallgame.GeneralPluginProvider;
import win.kanmodel.smallgame.Gamer.Gamer;

public class Vote {
	private int life = 0;
	private Map<Gamer,Integer> gamers = null;
	private BukkitTask task;
	private List<Gamer> result = null;
	public Vote(int life,List<Gamer> gamers){
		this.life = life;
		gamers.forEach(g->{
			this.gamers.put(g, 0);
		});
		Vote that = this;
		this.task = new BukkitRunnable(){
			public void run(){
				that.life-=1;
				if(that.life <= 1){
					this.cancel();
					that.life = 0;
					Stream<Entry<Gamer,Integer>> st = that.gamers.entrySet().stream();
					st.sorted(Comparator.comparing(e->e.getValue())).forEach(e->that.result.add(e.getKey()));
				}
			}
		}.runTaskTimerAsynchronously(GeneralPluginProvider.getInstance().getPlugin(),20,20);
	}
	public void setLife(int life){
		this.life = life;
	}
	public int getLife(){
		return this.life;
	}
	public List<Gamer> getResult(){
		return this.result;
	}
	public List<Gamer> getGamers(){
		List<Gamer> gamers = null;
		this.gamers.forEach((k,v)->{
			gamers.add(k);
		});
		return gamers;
	}
	public void vote(Gamer gamer){
		int votes = this.gamers.get(gamer);
		this.gamers.remove(gamer);
		this.gamers.put(gamer, votes);
	}
	public Map<Gamer,Integer> getVotes(){
		return this.gamers;
	}
	
	public void clear(){
		this.life = 0;
		this.task.cancel();
	}
	
}
