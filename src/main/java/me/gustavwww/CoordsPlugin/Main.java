package me.gustavwww.CoordsPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private EcoHandler ecoHandler;

    @Override
    public void onEnable() {
        super.onEnable();
        ecoHandler = EcoHandler.getInstance(this);
        if(!ecoHandler.setupEconomy()) {
            getLogger().info("ERROR: CoordsPlugin could not find Vault dependency. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("coords").setExecutor(new CoordsCommand(this));

        getLogger().info("Plugin Enabled.");
    }

}
