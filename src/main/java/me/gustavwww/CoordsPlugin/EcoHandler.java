package me.gustavwww.CoordsPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EcoHandler {

    public static EcoHandler instance = null;

    public static EcoHandler getInstance() {
        if (instance == null) {
            instance = new EcoHandler();
        }
        return instance;
    }

    private Economy eco = null;

    private EcoHandler() {}

    public boolean setupEconomy(JavaPlugin plugin) {
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

    public boolean withdrawPlayer(OfflinePlayer player, double amount) {
        return eco.withdrawPlayer(player, amount).transactionSuccess();
    }

}
