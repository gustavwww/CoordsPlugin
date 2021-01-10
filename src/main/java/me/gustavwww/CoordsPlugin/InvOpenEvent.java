package me.gustavwww.CoordsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class InvOpenEvent implements Listener {

    private final Config config = Config.getInstance();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equalsIgnoreCase(config.translateColor(config.menuTitle))) {
            return;
        }

        ItemStack item = event.getCurrentItem();
        if (item != null) {
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            if (meta != null && meta.hasOwner()) {
                OfflinePlayer clicked = meta.getOwningPlayer();
                performPurchaseCoordinates((Player) event.getWhoClicked(), clicked);
            }
        }

        event.setCancelled(true);
    }

    private void performPurchaseCoordinates(Player sender, OfflinePlayer victim) {
        if (!victim.isOnline()) {
            sender.sendMessage(Config.getInstance().replaceAndTranslateColor(config.playerOffline, sender, victim.getPlayer()));
            return;
        }
        if (!EcoHandler.getInstance().withdrawPlayer(sender, config.price)) {
            sender.sendMessage(config.replaceAndTranslateColor(config.notEnoughMoney, sender, victim.getPlayer()));
            return;
        }
        Player vic = victim.getPlayer();

        List<String> messages = config.replaceAndTranslateColor(config.coordMessage, sender, victim.getPlayer());
        for (String msg : messages) {
            String replaced = msg.replaceAll("%worldtype%", vic.getLocation().getWorld().getEnvironment().toString())
                    .replaceAll("%x%", String.valueOf(vic.getLocation().getBlockX()))
                    .replaceAll("%y%", String.valueOf(vic.getLocation().getBlockY()))
                    .replaceAll("%z%", String.valueOf(vic.getLocation().getBlockZ()));

            sender.sendMessage(replaced);
        }

        if (config.broadcast) {
            List<String> broadcast = config.replaceAndTranslateColor(config.broadcastMessage, sender, victim.getPlayer());
            for (String msg : broadcast) {
                Bukkit.broadcastMessage(msg);
            }
        }

    }

}
