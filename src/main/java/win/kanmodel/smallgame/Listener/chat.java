package win.kanmodel.smallgame.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import win.kanmodel.smallgame.GeneralPluginProvider;
import win.kanmodel.smallgame.Lobby;
import win.kanmodel.smallgame.Gamer.Gamers;

public class chat implements Listener {
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		Lobby lobby = GeneralPluginProvider.getLobby();
		if(!lobby.getList().contains(e.getPlayer()))
			return;
		String message = e.getMessage();
		e.setCancelled(true);
		GeneralPluginProvider.getGamers().getGamer(e.getPlayer()).getCurrentChannel().broadcastMessage(message);
	}
}
