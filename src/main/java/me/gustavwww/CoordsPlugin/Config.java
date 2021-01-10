package me.gustavwww.CoordsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private static Config instance;

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String menuTitle;
    public double price;
    public String itemTitle;
    public List<String> itemDescription;
    public String notEnoughMoney;
    public String playerOffline;
    public boolean broadcast;
    public List<String> broadcastMessage;
    public List<String> coordMessage;

    private Config() {}

    public void loadConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();

        menuTitle = plugin.getConfig().getString("MenuTitle");
        price = plugin.getConfig().getDouble("Price");
        itemTitle = plugin.getConfig().getString("ItemTitle");
        itemDescription = plugin.getConfig().getStringList("ItemDesc");
        notEnoughMoney = plugin.getConfig().getString("NotEnoughMoney");
        playerOffline = plugin.getConfig().getString("PlayerOffline");
        broadcast = plugin.getConfig().getBoolean("Broadcast");
        broadcastMessage = plugin.getConfig().getStringList("BroadcastMessage");
        coordMessage = plugin.getConfig().getStringList("CoordMessage");
    }

    public String translateColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String replaceAndTranslateColor(String s, Player sender, Player victim) {
        String a = translateColor(s);
        return a.replaceAll("%victim%", victim.getName())
                .replaceAll("%victim_display%", victim.getDisplayName())
                .replaceAll("%price%", String.valueOf(price))
                .replaceAll("%buyer%", sender.getName())
                .replaceAll("%buyer_display%", sender.getDisplayName());
    }

    public List<String> replaceAndTranslateColor(List<String> s, Player sender, Player victim) {
        List<String> l = new ArrayList<>();
        for (String str : s) {
            l.add(replaceAndTranslateColor(str, sender, victim));
        }
        return l;
    }

}
