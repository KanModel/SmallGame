package win.kanmodel.smallgame;

/**
 * Created by kgdwhsk on 2017/2/6.
 */

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import win.kanmodel.smallgame.Gamer.Gamers;

import java.io.File;

public class GeneralPluginProvider
{
    public static final String NAME = "[Danganronpa]";
    public static Lobby lobby = new Lobby();
    private static GeneralPluginProvider instance = new GeneralPluginProvider();
    private Plugin plugin;
    private static Gamers gamers = new Gamers();
    
    public static GeneralPluginProvider getInstance(){
        return instance;
    }
    /*public JudgePlayerAPIX getJudgePlayerAPI()
    {
        return new JudgePlayerAPIX();
    }*/

    public void setUpPluginGetter(Plugin pl) {
        this.plugin = pl;
    }

    public File getDataFolder() {
        return this.plugin.getDataFolder();
    }

    public FileConfiguration getConfig() {
        return this.plugin.getConfig();
    }

    public void saveDefaultConfig() {
        this.plugin.saveDefaultConfig();
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public static String getNAME() {
        return NAME;
    }

    public static Lobby getLobby() {
        return lobby;
    }
    
    public static Gamers getGamers(){
    	return gamers;
    }
}
