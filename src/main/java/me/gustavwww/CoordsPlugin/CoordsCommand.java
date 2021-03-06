package me.gustavwww.CoordsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CoordsCommand implements CommandExecutor {

    private final Config config = Config.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("CoordsPlugin: Command only executable by players.");
            return true;
        }

        Player p = (Player) sender;
        p.openInventory(createInventory(p));

        return true;
    }

    private Inventory createInventory(Player sender) {
        int online = Bukkit.getOnlinePlayers().size();
        int invSize = online + (9-(online % 9));

        Inventory inv = Bukkit.createInventory(null, invSize, config.translateColor(config.menuTitle));

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (config.skipOp && p.isOp()) {
                continue;
            }
            inv.addItem(getPlayerSkull(sender, p));
        }

        return inv;
    }

    private ItemStack getPlayerSkull(Player sender, Player victim) {

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);

        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(victim);
        meta.setDisplayName(config.replaceAndTranslateColor(config.itemTitle, sender, victim));
        meta.setLore(config.replaceAndTranslateColor(config.itemDescription, sender, victim));

        skull.setItemMeta(meta);
        return skull;
    }

}
