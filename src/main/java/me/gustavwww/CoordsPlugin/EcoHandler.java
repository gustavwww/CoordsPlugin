package me.gustavwww.CoordsPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EcoHandler {

    public static EcoHandler instance = null;

    public static EcoHandler getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new EcoHandler(plugin);
        }
        return instance;
    }

    private final JavaPlugin plugin;
    private Economy eco = null;

    private EcoHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }

        eco = rsp.getProvider();
        return true;
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        return eco.withdrawPlayer(player, amount);
    }

}
