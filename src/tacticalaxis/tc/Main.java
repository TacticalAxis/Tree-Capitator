package tacticalaxis.tc;

import org.bukkit.plugin.java.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;

public class Main extends JavaPlugin {
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents((Listener) new TreeHandler(), (Plugin) this);
    }
}
