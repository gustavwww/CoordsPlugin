package me.gustavwww.CoordsPlugin;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class InvOpenEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getClickedInventory();

        for (ItemStack i : inv.getContents()) {
            if (i.getItemMeta() instanceof SkullMeta) {
                SkullMeta meta = (SkullMeta) i.getItemMeta();
                if (meta.hasOwner()) {
                    OfflinePlayer clicked = meta.getOwningPlayer();
                }
            }
        }

        event.setCancelled(true);
    }

}
