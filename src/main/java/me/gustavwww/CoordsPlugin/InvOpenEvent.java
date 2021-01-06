package me.gustavwww.CoordsPlugin;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class InvOpenEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equalsIgnoreCase(Config.getInstance().translateColor(Config.getInstance().menuTitle))) {
            return;
        }

        Inventory inv = event.getClickedInventory();

        for (ItemStack i : inv.getContents()) {
            if (i.getItemMeta() instanceof SkullMeta) {
                SkullMeta meta = (SkullMeta) i.getItemMeta();
                if (meta.hasOwner()) {
                    OfflinePlayer clicked = meta.getOwningPlayer();
                    performPurchaseCoordinates((Player) event.getWhoClicked(), clicked);
                }
            }
        }

        event.setCancelled(true);
    }

    private void performPurchaseCoordinates(Player sender, OfflinePlayer victim) {
        if (!victim.isOnline()) {
            sender.sendMessage(Config.getInstance().replaceAndTranslateColor(Config.getInstance().playerOffline, victim.getPlayer()));
            return;
        }
        if (!EcoHandler.getInstance().withdrawPlayer(sender, Config.getInstance().price)) {
            sender.sendMessage(Config.getInstance().replaceAndTranslateColor(Config.getInstance().notEnoughMoney, victim.getPlayer()));
            return;
        }
        Player p = victim.getPlayer();

        List<String> messages = Config.getInstance().replaceAndTranslateColor(Config.getInstance().coordMessage, victim.getPlayer());
        for (String msg : messages) {
            String replaced = msg.replaceAll("%worldtype%", p.getLocation().getWorld().getEnvironment().toString())
                    .replaceAll("%x%", String.valueOf(p.getLocation().getBlockX()))
                    .replaceAll("%y%", String.valueOf(p.getLocation().getBlockY()))
                    .replaceAll("%z%", String.valueOf(p.getLocation().getBlockZ()));

            sender.sendMessage(replaced);
        }
    }

}
