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
    public List<String> coordMessage;

    private Config() {}

    public void loadConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();

        menuTitle = plugin.getConfig().getString("MenuTitle");
        price = plugin.getConfig().getDouble("price");
        itemTitle = plugin.getConfig().getString("ItemTitle");
        itemDescription = plugin.getConfig().getStringList("ItemDesc");
        notEnoughMoney = plugin.getConfig().getString("NotEnoughMoney");
        playerOffline = plugin.getConfig().getString("PlayerOffline");
        coordMessage = plugin.getConfig().getStringList("CoordMessage");
    }

    public String translateColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public String replaceAndTranslateColor(String s, Player p) {
        String a = translateColor(s);
        return a.replaceAll("%name%", p.getName())
                .replaceAll("%displayname%", p.getDisplayName())
                .replaceAll("%price%", String.valueOf(price));
    }

    public List<String> replaceAndTranslateColor(List<String> s, Player p) {
        List<String> l = new ArrayList<>();
        for (String str : s) {
            l.add(replaceAndTranslateColor(str, p));
        }
        return l;
    }

}
