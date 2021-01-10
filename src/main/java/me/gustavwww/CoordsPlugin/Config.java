package me.gustavwww.CoordsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
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
    public boolean skipOp;
    public boolean giveCompass;
    public String compassTitle;
    public List<String> compassDescription;
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

        FileConfiguration config = plugin.getConfig();

        menuTitle = config.getString("MenuTitle");
        price = config.getDouble("Price");
        skipOp = config.getBoolean("SkipOp");
        giveCompass = config.getBoolean("GiveCompass");
        compassTitle = config.getString("CompassTitle");
        compassDescription = config.getStringList("CompassDesc");
        itemTitle = config.getString("ItemTitle");
        itemDescription = config.getStringList("ItemDesc");
        notEnoughMoney = config.getString("NotEnoughMoney");
        playerOffline = config.getString("PlayerOffline");
        broadcast = config.getBoolean("Broadcast");
        broadcastMessage = config.getStringList("BroadcastMessage");
        coordMessage = config.getStringList("CoordMessage");
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
