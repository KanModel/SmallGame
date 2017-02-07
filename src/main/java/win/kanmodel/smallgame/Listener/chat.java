package win.kanmodel.smallgame.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import win.kanmodel.smallgame.Gamer.Gamer;
import win.kanmodel.smallgame.GeneralPluginProvider;
import win.kanmodel.smallgame.Lobby;
import win.kanmodel.smallgame.Gamer.Gamers;

public class chat implements Listener {
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		Player player = e.getPlayer();
		Lobby lobby = GeneralPluginProvider.getLobby();
		if(!lobby.getList().contains(player))
			return;
		String message = e.getMessage();
		e.setCancelled(true);
//		if (GeneralPluginProvider.getGamers().getGamer(player).getType() == Gamer.GAMER_JUDGE){
//
//		}else {
//
//		}
		GeneralPluginProvider.getGamers().getGamer(e.getPlayer()).getCurrentChannel().broadcastMessage(message ,e.getPlayer());
	}
}
