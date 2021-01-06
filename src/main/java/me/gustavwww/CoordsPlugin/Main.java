package me.gustavwww.CoordsPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        EcoHandler ecoHandler = EcoHandler.getInstance();
        if(!ecoHandler.setupEconomy(this)) {
            getLogger().info("ERROR: CoordsPlugin could not find Vault dependency. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Config.getInstance().loadConfig(this);

        getServer().getPluginManager().registerEvents(new InvOpenEvent(), this);
        getCommand("coords").setExecutor(new CoordsCommand());

        getLogger().info("Plugin Enabled.");
    }

}
